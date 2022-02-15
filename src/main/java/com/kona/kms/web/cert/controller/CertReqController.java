package com.kona.kms.web.cert.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
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
import com.kona.kms.utils.Util;
import com.kona.kms.web.cert.BrandType;
import com.kona.kms.web.cert.bo.CertReqBO;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.cert.view.CertReqExcelView;
import com.kona.kms.web.profile.bo.KeyProfileBO;
import com.kona.kms.web.profile.model.KeyProfileModel;
import com.kona.kms.web.utils.AjaxResponse;
import com.kona.kms.web.utils.AjaxStatus;
import com.kona.kms.web.utils.GridPageData;

@Controller
@RequestMapping("/CertReq")
public class CertReqController {

	private static final Logger logger = LoggerFactory.getLogger(CertReqController.class);
	
	@Autowired
	private CertReqBO certReqBO;
	
	@Autowired
	private KeyProfileBO keyProfileBO;
	
	@Autowired
	private CertReqExcelView certReqExcelView;
	
	@RequestMapping("/search")
	public String searchView(Model model) throws Exception{
		
		return "/cert/cert_req_list";
	}
	

	@RequestMapping(value="/searchList", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody GridPageData<CertificateModel> searchList(@ModelAttribute CertificateModel condition) throws Exception{
		
		GridPageData<CertificateModel> values = certReqBO.getCertificateList(condition);
		
		return values;
	}
	
	@RequestMapping(value="/popup/request")
	public String requestView(Model model) throws Exception{
		
		return "/cert/cert_req_new";
	}
	
	
	@RequestMapping(value="/popup/requestCert", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse insertCertificate(@ModelAttribute CertificateModel model, Principal principal, HttpServletRequest req) throws Exception{		
		
		Date now = new Date();
		model.setRegistrationDate(now);
		model.setRegistrationUserID(principal.getName());
		model.setUpdateDate(now);
		model.setUpdateUserID(principal.getName());
		
		try{
			certReqBO.requestCertificate(model);
		}catch(KmsException e){
			logger.error("CertReqController: insertCertificate: " + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(e.getMsgcode(), new Locale((String)req.getSession().getAttribute("lan"))));
		}	
		catch(Exception e){
			logger.error("CertReqController: insertCertificate: " + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.CERT_REQ_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		AjaxResponse res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.CERT_REQ_SUCCESS,new Locale((String)req.getSession().getAttribute("lan"))));
		res.setCertificateUID(model.getCertificateUID());
		
		return res;
	}
	
	@RequestMapping(value="/popup/edit")
	public ModelAndView certificateEditView(@RequestParam("certiUID") String id) throws Exception{
		
		ModelAndView mv = new ModelAndView("/cert/cert_req_edit");
		
		CertificateModel cert = certReqBO.getCertificate(id);
		
		List<KeyProfileModel> profiles = keyProfileBO.getPairKeyProfiles(cert.getKeySubject());
		
		KeyProfileModel privateKey = null;
		KeyProfileModel publicKey = null;
		
		for(KeyProfileModel profile : profiles){
			if(profile.getKeyTypeCode().equals("PRIVATE")){
				privateKey = profile;
			}else{
				publicKey = profile;
			}
		}
		
		mv.addObject("cert", cert);
		mv.addObject("priKey", privateKey);
		mv.addObject("pubKey", publicKey);
		
		return mv;
	}
	
	@RequestMapping(value="/popup/editCert", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse updateCertificate(@ModelAttribute CertificateModel model, Principal principal, HttpServletRequest req) throws Exception{		
		
		Date now = new Date();
		
		model.setUpdateDate(now);
		model.setUpdateUserID(principal.getName());
		
		try{
			certReqBO.editCertificate(model);
		}catch(KmsException e){
			logger.error("CertReqController: updateCertificate: " + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(e.getMsgcode(), new Locale((String)req.getSession().getAttribute("lan"))));
		}	
		catch(Exception e){
			logger.error("CertReqController: updateCertificate: " + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.CERT_EDIT_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		AjaxResponse res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.CERT_EDIT_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
		res.setCertificateUID(model.getCertificateUID());
		
		return res;
	}
	
	@RequestMapping(value="/popup/detail", method=RequestMethod.POST)
	public ModelAndView getCertificateDetail(@RequestParam("certiUID") String id) throws Exception{
		
		ModelAndView mv = new ModelAndView("/cert/cert_req_detail");
		
		CertificateModel cert = certReqBO.getCertificate(id);
		
		List<KeyProfileModel> profiles = keyProfileBO.getPairKeyProfiles(cert.getKeySubject());
		
		KeyProfileModel privateKey = null;
		KeyProfileModel publicKey = null;
		
		for(KeyProfileModel profile : profiles){
			if(profile.getKeyTypeCode().equals("PRIVATE")){
				privateKey = profile;
			}else{
				publicKey = profile;
			}
		}
		
		mv.addObject("cert", cert);
		mv.addObject("priKey", privateKey);
		mv.addObject("pubKey", publicKey);
		
		return mv;				
	}
	
	@RequestMapping(value="/deleteCert", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse deleteCertificate(@RequestParam("certiUID") String[] certiUIDs, HttpServletRequest req) throws Exception{
		try{
			certReqBO.deleteCertificate(certiUIDs);
		}catch(Exception e){
			
			logger.error("CertReqController: deleteCertificate: " + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.CERT_DELETE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		return new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.CERT_DELETE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
	}
	
	@RequestMapping(value="/downloadFile", method=RequestMethod.POST)
	public String downloadProfile(HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		String certiUID = req.getParameter("certiUID");
		String type = req.getParameter("fileType");	
		
			
		CertificateModel model = certReqBO.getCertificate(certiUID);
		
		String filename = null;
		byte[] contents = null;	
		
		if("certi".equals(type)){
			
			filename = model.getCertiReqFileName();
			
			contents = model.getCertiReqBinaryFile();
		}else if("hash".equals(type)){
			
			filename = model.getHashValueFileName();
			
			if(model.getBrandType() == BrandType.MASTER || model.getBrandType() == BrandType.JCB){
				contents = Util.hexString2byteArray(model.getHashValue());
			}else{
				contents = model.getHashValue().getBytes();
			}
			
		}
		
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
		
		ModelAndView mv = new ModelAndView(this.certReqExcelView);
		
		
		mv.addObject("list", certReqBO.getCertificateListAll(condition));
		
		return mv;
	}
	
	@RequestMapping(value="/sendCert", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse sendCert(@RequestParam("certificateUID") String certificateUID, Principal principal, HttpServletRequest req) throws Exception{
		AjaxResponse res = null;
		try{
			
			certReqBO.sendCert(certificateUID, principal.getName());
			
		}catch(Exception e){
						
			logger.error("CertReqController: sendCert: " + e.getMessage());
			
			res = new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_DELETE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_DELETE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
		return res;
	}
	
	
	@ExceptionHandler(Exception.class)
	public void handleException(Exception e){
		logger.error("CertReqController: handleException: " + e.getMessage());	
		
	}
	
}
