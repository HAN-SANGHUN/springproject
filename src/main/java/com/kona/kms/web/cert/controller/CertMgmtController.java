package com.kona.kms.web.cert.controller;

import java.security.Principal;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.kona.kms.web.cert.bo.CertMgmtBO;
import com.kona.kms.web.cert.bo.CertReqBO;
import com.kona.kms.web.cert.model.CertiRegisterDTO;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.cert.view.CertMgmtExcelView;
import com.kona.kms.web.utils.AjaxResponse;
import com.kona.kms.web.utils.AjaxStatus;
import com.kona.kms.web.utils.GridPageData;

@Controller
@RequestMapping("/CertMgmt")
public class CertMgmtController {

	private static final Logger logger = LoggerFactory.getLogger(CertMgmtController.class);
	
	@Autowired
	private CertMgmtBO certMgmtBO;
	
	@Autowired
	private CertReqBO certReqBO;
	
	@Autowired
	private CertMgmtExcelView certMgmtExcelView;
	
	@RequestMapping("/search")
	public String searchView(Model model) throws Exception{
		
		return "/cert/cert_mgmt_list";
	}
	
	@RequestMapping(value="/searchList", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody GridPageData<CertificateModel> searchList(@ModelAttribute CertificateModel condition) throws Exception{
		
		logger.info("CertMgmtController: searchList: " + condition);
		GridPageData<CertificateModel> values = certMgmtBO.getCertificateList(condition);
		
		return values;
	}
	
	@RequestMapping(value="/popup/register")
	public ModelAndView registerView(@RequestParam("certificateUID") String certificateUID) throws Exception{
		
		ModelAndView mv = new ModelAndView("/cert/cert_mgmt_register");
		
		mv.addObject("cert", certMgmtBO.getCertificate(certificateUID));
		
		return mv;
	}
	
	@RequestMapping(value="/popup/registerCert", method=RequestMethod.POST)
	public ModelAndView registerCertificate(@ModelAttribute("certiRegisterDTO") CertiRegisterDTO file, Principal principal, HttpServletRequest req) throws Exception{	
		
		ModelAndView mv = new ModelAndView("/cert/cert_mgmt_detail");
		
		CertificateModel model = certMgmtBO.getCertificate(file.getCertificateUID());
		
		mv.addObject("cert", model);
		
		if(file.isEmpty()){
			String message = MsgManager.getInstance().getMessage(KMSCode.CERTI_REGISTER_FILE_EMPTY, new Locale((String)req.getSession().getAttribute("lan")));
			
			logger.info("CertMgmtController: parseProfile: " + message);
			
			mv.addObject("message", message);
			mv.setViewName("/cert/cert_mgmt_register");
			
			return mv;
		}
		
		try{
			certMgmtBO.registerCertificate(file, principal.getName());
			
			mv.addObject("cert", certMgmtBO.getCertificate(file.getCertificateUID()));
		}catch(KmsException e){
			
			logger.error("CertMgmtController: registerCertificate: KmsException: " + e.getMessage());	
			
			String message = MsgManager.getInstance().getMessage(e.getMsgcode(), new String[]{e.getMessage()}, new Locale((String)req.getSession().getAttribute("lan")));
			
			mv.addObject("message", message);
			mv.setViewName("/cert/cert_mgmt_register");
			
			return mv;
		}catch(Exception e){	
			
			logger.error("CertMgmtController: registerCertificate: Exception: " + e.getMessage());
			
			String message = MsgManager.getInstance().getMessage(KMSCode.CERTI_REGISTER_FAIL, new Locale((String)req.getSession().getAttribute("lan")));
			
			mv.addObject("message", message);
			mv.setViewName("/cert/cert_mgmt_register");
			
			return mv;
		}		
		
		return mv;
	}
	
	@RequestMapping(value="/popup/detail")
	public ModelAndView detailView(@RequestParam("certificateUID") String certificateUID) throws Exception{
		ModelAndView mv = new ModelAndView("/cert/cert_mgmt_detail");
		mv.addObject("cert", certMgmtBO.getCertificate(certificateUID));
		return mv;
	}
	
	@RequestMapping(value="/popup/approval")
	public ModelAndView approvalView(@RequestParam("certificateUID") String certificateUID) throws Exception{
		ModelAndView mv = new ModelAndView("/cert/cert_mgmt_approval");
		mv.addObject("cert", certMgmtBO.getCertificate(certificateUID));
		return mv;
	}
	
	@RequestMapping(value="/popup/approvalCert")
	public @ResponseBody AjaxResponse approvalCertificate(@ModelAttribute CertificateModel model, Principal principal, HttpServletRequest req) throws Exception{	
		Date now = new Date();
		model.setUpdateDate(now);
		model.setUpdateUserID(principal.getName());
		
		try{
			certMgmtBO.approvalCertificate(model);
		}catch(Exception e){
			logger.error("CertMgmtController: approvalCertificate: " + e.getMessage());
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.CERT_REQ_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		AjaxResponse res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.CERT_REQ_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
		res.setCertificateUID(model.getCertificateUID());
		
		return res;
	}
	
	@RequestMapping(value="/downloadFile", method=RequestMethod.POST)
	public String downloadProfile(HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		String certiUID = req.getParameter("certiUID");
		
			
		CertificateModel model = certMgmtBO.getCertificate(certiUID);
		
//		int index = model.getCertiResFileName().lastIndexOf("/");
//		String filename =  model.getCertiResFileName().substring(index+1);
		
		int index;
		String filename;
		if (model.getCertiResFileName() != null){
			index = model.getCertiResFileName().lastIndexOf("/");
			filename = model.getCertiResFileName().substring(index + 1);
		} else{
			filename = "No_Certificate_file";
		}
		
		byte[] contents = model.getCertiResBinaryFile();	
				
		res.setContentType("text/plain");
		res.setHeader("Content-Disposition", "attachment;filename="+filename);
				
		ServletOutputStream out = res.getOutputStream();
		out.write(contents);
		out.flush();			
		out.close();		
		
		return null;
	}	
	
	@RequestMapping(value="/excel", method=RequestMethod.POST)
	public ModelAndView excel(@ModelAttribute CertificateModel condition) throws Exception{
		
		ModelAndView mv = new ModelAndView(this.certMgmtExcelView);
		
		
		mv.addObject("list", certMgmtBO.getCertificateListAll(condition));
		
		return mv;
	}
	
	@RequestMapping(value="/sendCert", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse sendCert(@RequestParam("certificateUID") String certificateUID, Principal principal, HttpServletRequest req) throws Exception{
		AjaxResponse res = null;
		try{
			
			certReqBO.sendCert(certificateUID, principal.getName());
			
		}catch(Exception e){
						
			logger.error("CertMgmtController: sendCert: " + e.getMessage());
			
			res = new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_DELETE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_DELETE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
		return res;
	}
	
	
	@ExceptionHandler(Exception.class)
	public void handleException(Exception e){
		logger.error("CertMgmtController: handleException: " + e.getMessage());	
		
	} 
}
