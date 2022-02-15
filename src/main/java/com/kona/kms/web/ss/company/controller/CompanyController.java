package com.kona.kms.web.ss.company.controller;

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
import com.kona.kms.framework.MsgManager;
import com.kona.kms.web.ss.company.bo.CompanyBO;
import com.kona.kms.web.ss.company.model.CompanyModel;
import com.kona.kms.web.ss.company.view.CompanyExcelView;
import com.kona.kms.web.utils.AjaxResponse;
import com.kona.kms.web.utils.AjaxStatus;
import com.kona.kms.web.utils.GridPageData;

@Controller
@RequestMapping("/CompSetting")
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	private CompanyBO companyBO;
	
	@Autowired
	private CompanyExcelView CompanyExcelView;
	
	@RequestMapping("/search")
	public String searchView(Model model) throws Exception{
		
		return "/ss/company/company_list";
	}
	
	@RequestMapping(value="/searchList", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody GridPageData<CompanyModel> searchList(@ModelAttribute CompanyModel condition) throws Exception{
		
		GridPageData<CompanyModel> values = companyBO.getCompanyList(condition);
		
		return values;
	}	

	@RequestMapping(value="/excel", method=RequestMethod.POST)
	public ModelAndView excel(@ModelAttribute CompanyModel condition) throws Exception{
		
		ModelAndView mv = new ModelAndView(this.CompanyExcelView);
		
		mv.addObject("list", companyBO.getCompanyListAll(condition));
		
		return mv;
	}			
		
	@RequestMapping(value="/popup/detail", method=RequestMethod.POST)
	public ModelAndView getCompanyDetail(@RequestParam("companyId") String companyId) throws Exception{
		
		ModelAndView mv = new ModelAndView("/ss/company/company_detail");
		
		CompanyModel value = companyBO.selectCompany(companyId);
		
		mv.addObject("company", value);
		
		return mv;				
	}
	
	@RequestMapping(value="/popup/edit")
	public ModelAndView CompanyEditView(@RequestParam("companyId") String companyId) throws Exception{
		
		ModelAndView mv = new ModelAndView("/ss/company/company_edit");
		
		mv.addObject("company", companyBO.selectCompany(companyId));
		
		return mv;
	}
	

	@RequestMapping(value="/popup/editCompany", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse updateCompany(@ModelAttribute CompanyModel model, Principal principal, HttpServletRequest req) throws Exception{		
		
		model.setUpdateUserId(principal.getName());
		model.setUpdateDate(new Date());
						
		try{
			companyBO.updateCompany(model);
		}catch(Exception e){
			
			logger.error("CompanyController: updateCompany: "+ e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.COMPANY_EDIT_FAIL,new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		return new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.COMPANY_EDIT_SUCCESS,new Locale((String)req.getSession().getAttribute("lan"))));
	}	

	@RequestMapping(value="/deleteCompany", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse deleteCompany(@RequestParam("companyId") String[] companyId, Principal principal, HttpServletRequest req) throws Exception{
		
		try{
			companyBO.deleteCompany(companyId, principal.getName());
		}catch(Exception e){
			
			logger.error("CompanyController: deleteCompany: " + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.COMPANY_DELETE_FAIL,new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		return new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.COMPANY_DELETE_SUCCESS,new Locale((String)req.getSession().getAttribute("lan"))));
	}
	
	@ExceptionHandler(Exception.class)
	public void handleException(Exception e){
		logger.error("CompanyController: handleException: " + e.getMessage());	
		
	}
	
}
