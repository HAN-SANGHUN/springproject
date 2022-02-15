package com.kona.kms.web.ss.hsm.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.kona.kms.web.ss.hsm.model.HsmMetaModel;
import com.kona.kms.web.ss.hsm.view.HsmMetaExcelView;
import com.kona.kms.web.utils.AjaxResponse;
import com.kona.kms.web.utils.AjaxStatus;
import com.kona.kms.web.utils.GridPageData;

@Controller
@RequestMapping("/HsmSetting")
public class HsmMetaController {
	
	private static final Logger logger = LoggerFactory.getLogger(HsmMetaController.class);

	@Autowired
	private HsmMetaBO hsmMetaBO;
	
	@Autowired
	private HsmMetaExcelView HsmMetaExcelView;
	
	@RequestMapping("/search")
	public ModelAndView searchView() throws Exception{
		
		ModelAndView mv = new ModelAndView("/ss/hsm/hsm_meta_list");
		
		HsmMetaModel condition = new HsmMetaModel();
		condition.setSortname("hsmNo");
		condition.setSortorder("asc");
		
		List<HsmMetaModel> models = hsmMetaBO.getPhysicalHsmListAll(condition);
		
		boolean isActivated = true;
		
		if(models != null && !models.isEmpty()){
			for(HsmMetaModel model : models){
				if(model.getStatusCode().equals("Inactive")){
					isActivated = false;
					break;
				}
			}
		}
		else{
			isActivated = false;
		}
		
		mv.addObject("activateFlag", isActivated);
		
		return mv;
	}
	
	@RequestMapping(value="/searchList", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody GridPageData<HsmMetaModel> searchList(@ModelAttribute HsmMetaModel condition) throws Exception{
		
		GridPageData<HsmMetaModel> values = hsmMetaBO.getPhysicalHsmList(condition);
		
		return values;
	}

	@RequestMapping(value="/popup/detail", method=RequestMethod.POST)
	public ModelAndView getPhysicalHsmDetail(@RequestParam("hsmNo") int hsmNo) throws Exception{
		
		ModelAndView mv = new ModelAndView("/ss/hsm/hsm_meta_detail");
		
		HsmMetaModel value = hsmMetaBO.getPhysicalHsm(hsmNo);
		
		mv.addObject("hsm", value);
		
		return mv;				
	}
	
	@RequestMapping(value="/popup/edit")
	public ModelAndView hsmMetaEditView(@RequestParam("hsmNo") int hsmNo) throws Exception{
		
		ModelAndView mv = new ModelAndView("/ss/hsm/hsm_meta_edit");
		
		mv.addObject("hsm", hsmMetaBO.getPhysicalHsm(hsmNo));
		
		return mv;
	}
	
	@RequestMapping(value="/popup/editHsm", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse updatePhysicalHsm(@ModelAttribute HsmMetaModel model, Principal principal, HttpServletRequest req) throws Exception{		
		
		model.setUpdateUserId(principal.getName());
		model.setUpdateDate(new Date());
		
		try{
			hsmMetaBO.updatePhysicalHsm(model);
		}catch(Exception e){
			
			logger.error("HsmMetaController: updatePhysicalHsm: " + e.getMessage());
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.HSM_EDIT_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		return new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.HSM_EDIT_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
	}
	
	@RequestMapping(value="/popup/add")
	public ModelAndView hsmMetaNewView() throws Exception{
		
		ModelAndView mv = new ModelAndView("/ss/hsm/hsm_meta_new");
		
		HsmMetaModel model = hsmMetaBO.getPhysicalHsmPrimary();
		
		if(model != null){
			mv.addObject("hsm", model);
			mv.addObject("primary", false);
		}else{
			mv.addObject("primary", true);
		}		
		
		return mv;
	}
	
	@RequestMapping(value="/popup/addHsm", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse insertPhysicalHsm(@ModelAttribute HsmMetaModel model, Principal principal, HttpServletRequest req) throws Exception{		
		
		Calendar cal = Calendar.getInstance();
		
		model.setStatusCode("Inactive");
		model.setRegistrationUserId(principal.getName());
		model.setRegistrationDate(cal.getTime());
		model.setUpdateUserId(principal.getName());
		model.setUpdateDate(cal.getTime());
		
		try{
			hsmMetaBO.insertPhysicalHsm(model);
		}catch(Exception e){
			
			logger.error("HsmMetaController: insertPhysicalHsm" + e.getMessage());
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.HSM_CREATE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		AjaxResponse res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.HSM_CREATE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
		
		res.setHsmNo(model.getHsmNo());
		
		return res;
	}
	
	@RequestMapping(value="/excel", method=RequestMethod.POST)
	public ModelAndView excel(@ModelAttribute HsmMetaModel condition) throws Exception{
		
				
		ModelAndView mv = new ModelAndView(this.HsmMetaExcelView);
		
		mv.addObject("list", hsmMetaBO.getPhysicalHsmListAll(condition));
		
		return mv;
	}
		
	@RequestMapping(value="/deleteHsm", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse deletePhysicalHsm(@RequestParam("hsmNo") String[] hsmNo, Principal principal, HttpServletRequest req) throws Exception{
		
		try{
			hsmMetaBO.deletePhysicalHsm(hsmNo, principal.getName());
		}catch(Exception e){
			
			logger.error("HsmMetaController: deletePhysicalHsm: " + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.HSM_DELETE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		return new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.HSM_DELETE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
	}	
	
	@RequestMapping(value="/activateHsm", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse activateHsm(HttpServletRequest req) throws Exception{
		
		try{
			hsmMetaBO.activateHsm();
		}catch(KmsException e){
			logger.error("HsmMetaController: activateHsm: " + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(e.getMsgcode()));
		}
		catch(Exception e){
			
			logger.error("HsmMetaController: deletePhysicalHsm" + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.HSM_ACTIVATE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		return new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.HSM_ACTIVATE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
	}	
	
	@ExceptionHandler(Exception.class)
	public void handleException(Exception e){
		logger.error("HsmMetaController: handleException: " + e.getMessage());	
		
	}
	
	
}
