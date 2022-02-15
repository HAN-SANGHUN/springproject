package com.kona.kms.web.ss.token.controller;

import java.security.Principal;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.framework.MsgManager;
import com.kona.kms.web.ss.hsm.bo.HsmMetaBO;
import com.kona.kms.web.ss.token.bo.TokenMgmtBO;
import com.kona.kms.web.ss.token.model.SlotModel;
import com.kona.kms.web.ss.token.model.TokenModel;
import com.kona.kms.web.ss.token.view.TokenExcelView;
import com.kona.kms.web.ss.user.bo.KmsUserBO;
import com.kona.kms.web.utils.AjaxResponse;
import com.kona.kms.web.utils.AjaxStatus;
import com.kona.kms.web.utils.GridPageData;

@Controller
@RequestMapping("/TokenSetting")
public class TokenMgmtController {

	private static final Logger logger = LoggerFactory.getLogger(TokenMgmtController.class);
	
	@Autowired
	private TokenMgmtBO tokenMgmtBO;
	
	@Autowired
	private HsmMetaBO hsmMetaBO;
	
	@Autowired
	private KmsUserBO kmsUserBO;
	
	@Autowired
	private TokenExcelView tokenExcelView;
	
	@RequestMapping("/search")
	public String searchView(Model model) throws Exception{
		
		return "/ss/token/token_list";
	}
	
	@RequestMapping(value="/searchList", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody GridPageData<TokenModel> searchList(@ModelAttribute TokenModel condition) throws Exception{
		
		GridPageData<TokenModel> values = tokenMgmtBO.getTokenList(condition);
		
		return values;
	}
	
	@RequestMapping(value="/popup/generate")
	public ModelAndView tokenNewView(Model model, Principal principal) throws Exception{
		
		ModelAndView mv = new ModelAndView("/ss/token/token_new");
		
		mv.addObject("hsm", hsmMetaBO.getPhysicalHsmPrimary());
				
		return mv;
	}
	
	@RequestMapping(value="/popup/generateToken", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse insertToken(@ModelAttribute TokenModel model, Principal principal, HttpServletRequest req) throws Exception{
		Date now = new Date();
		model.setRegistrationDate(now);
		model.setRegistrationUserID(principal.getName());
		model.setUpdateDate(now);
		model.setUpdateUserID(principal.getName());	
		
		try{
			tokenMgmtBO.initToken(model);
		}catch(Exception e){
			
			logger.error("TokenMgmtController: initToken: " + e.getMessage());
			new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.TOKEN_ACTIVATE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}		
		
		return new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.TOKEN_ACTIVATE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
	}
	
	@RequestMapping(value="/popup/edit")
	public ModelAndView tokenEditView(@RequestParam("tokenNo") long tokenNo, Principal principal) throws Exception{
		
		ModelAndView mv = new ModelAndView("/ss/token/token_edit");
		
		mv.addObject("token", tokenMgmtBO.getToken(tokenNo));
		mv.addObject("hsm", hsmMetaBO.getPhysicalHsmPrimary());
				
		return mv;
	}
	
	@RequestMapping(value="/popup/editToken", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse updateToken(@ModelAttribute TokenModel model, Principal principal, HttpServletRequest req) throws Exception{		
		
		model.setUpdateUserID(principal.getName());
		model.setUpdateDate(new Date());
				
		try{
			tokenMgmtBO.updateToken(model);
		}catch(Exception e){
			logger.error("TokenMgmtController:updateToken: "+ e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.TOKEN_EDIT_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
				
		return new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.TOKEN_EDIT_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
	}
	
	@RequestMapping(value="/popup/detail", method=RequestMethod.POST)
	public ModelAndView getTokenDetail(@RequestParam("tokenNo") long tokenNo) throws Exception{
		
		ModelAndView mv = new ModelAndView("/ss/token/token_detail");
		
		TokenModel value = tokenMgmtBO.getToken(tokenNo);
		
		mv.addObject("token", value);
		
		return mv;				
	}	
	
	@RequestMapping(value="/popup/slotList", method=RequestMethod.POST)
	public @ResponseBody GridPageData<SlotModel> searchList(@ModelAttribute SlotModel condition) throws Exception{
		
		GridPageData<SlotModel> values = tokenMgmtBO.getSlotList(condition);
		
		return values;
	}
	
	
	@RequestMapping(value="/excel", method=RequestMethod.POST)
	public ModelAndView excel(@ModelAttribute TokenModel condition) throws Exception{
		
		ModelAndView mv = new ModelAndView(this.tokenExcelView);
			
		mv.addObject("list", tokenMgmtBO.getTokenListAll(condition));
		
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public void handleException(Exception e){
		logger.error("TokenMgmtController: handleException: " + e.getMessage());	
		
	}
}
