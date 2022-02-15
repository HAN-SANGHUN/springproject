package com.kona.kms.web.ss.user.controller;

import java.security.Principal;
import java.util.Calendar;
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
import com.kona.kms.web.ss.user.bo.KmsUserBO;
import com.kona.kms.web.ss.user.model.KmsUserModel;
import com.kona.kms.web.ss.user.view.UserExcelView;
import com.kona.kms.web.utils.AjaxResponse;
import com.kona.kms.web.utils.AjaxStatus;
import com.kona.kms.web.utils.GridPageData;

@Controller
@RequestMapping("/UserSetting")
public class KmsUserController {

	private static final Logger logger = LoggerFactory.getLogger(KmsUserController.class);
	
	@Autowired
	private KmsUserBO kmsUserBO;
	
	@Autowired
	private UserExcelView userExcelView;
	
	@RequestMapping(value="/search")
	public String searchView() throws Exception{
		
		return "/ss/user/kms_user_list";
	}
	
	@RequestMapping(value="/searchList", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody GridPageData<KmsUserModel> searchList(@ModelAttribute KmsUserModel condition) throws Exception{
		
		GridPageData<KmsUserModel> values = kmsUserBO.getKmsUserList(condition);
		
		return values;
	}
	
	@RequestMapping(value="/popup/add")
	public String kmsUserNewView(Model model) throws Exception{
		
		return "/ss/user/kms_user_new";
	}
	
	
	@RequestMapping(value="/popup/addUser", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody AjaxResponse insertKmsUser(@ModelAttribute KmsUserModel model, Principal principal, HttpServletRequest req) throws Exception{		
				
		Calendar cal = Calendar.getInstance();
		
		model.setRegistrationUserId(principal.getName());
		model.setRegistrationDate(cal.getTime());
		model.setUpdateUserId(principal.getName());
		model.setUpdateDate(cal.getTime());
		
		
		try{
			kmsUserBO.insertKmsUser(model);
		}catch(KmsException e){
			logger.error("KmsUserController: insertKmsUser: " + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(e.getMsgcode(), new Locale((String)req.getSession().getAttribute("lan"))));
		}
		catch(Exception e){
			
			logger.error("KmsUserController: insertKmsUser: " + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.USER_CREATE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		return new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.USER_CREATE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
	}
	
	@RequestMapping(value="/popup/edit")
	public ModelAndView kmsUserEditView(@RequestParam("userId") String userId) throws Exception{
		
		ModelAndView mv = new ModelAndView("/ss/user/kms_user_edit");
		
		mv.addObject("user", kmsUserBO.getKmsUser(userId));
		
		return mv;
	}
	
	@RequestMapping(value="/popup/editUser", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse updateKmsUser(@ModelAttribute KmsUserModel model, Principal principal, HttpServletRequest req) throws Exception{		
		
		model.setUpdateUserId(principal.getName());
		model.setUpdateDate(new Date());
				
		try{
			kmsUserBO.updateKmsUser(model);
		}catch(Exception e){
			
			logger.error("KmsUserController: updateKmsUser: " + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.USER_EDIT_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		return new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.USER_EDIT_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
	}
	
	@RequestMapping(value="/deleteUser", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse deleteKmsUser(@RequestParam("userId") String[] userId, Principal principal, HttpServletRequest req) throws Exception{
		
		try{
			kmsUserBO.deleteKmsUser(userId, principal.getName());
		}catch(Exception e){
			
			logger.error("KmsUserController: deletKmsUser: " + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.USER_DELETE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		return new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.USER_DELETE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
	}
	
	@RequestMapping(value="/popup/detail", method=RequestMethod.POST)
	public ModelAndView getKmsUserDetail(@RequestParam("userId") String userId) throws Exception{
		
		ModelAndView mv = new ModelAndView("/ss/user/kms_user_detail");
		
		KmsUserModel value = kmsUserBO.getKmsUser(userId);
		
		mv.addObject("user", value);
		
		return mv;				
	}
	
	
	@RequestMapping(value="/excel", method=RequestMethod.POST)
	public ModelAndView excel(@ModelAttribute KmsUserModel condition) throws Exception{
		
		ModelAndView mv = new ModelAndView(this.userExcelView);
		
		
		mv.addObject("list", kmsUserBO.getKmsUserListAll(condition));
		
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public void handleException(Exception e){
		logger.error("KmsUserController: handleException: " + e.getMessage());	
		
	}
}
