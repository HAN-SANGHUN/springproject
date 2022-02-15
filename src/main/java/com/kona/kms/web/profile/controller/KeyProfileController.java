package com.kona.kms.web.profile.controller;



import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.cxf.common.util.StringUtils;
import org.globalplatform.namespaces.systems_profiles._1_1.KeyProfile;
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
import com.kona.kms.framework.GpMsgBinder;
import com.kona.kms.framework.MsgManager;
import com.kona.kms.web.profile.ConvertUtil;
import com.kona.kms.web.profile.bo.KeyProfileBO;
import com.kona.kms.web.profile.model.KeyProfileModel;
import com.kona.kms.web.profile.model.KeyProfileModel1;
import com.kona.kms.web.profile.model.KeyValueDTO;
import com.kona.kms.web.profile.model.PairKeyDTO;
import com.kona.kms.web.profile.model.ProfileExportDTO;
import com.kona.kms.web.profile.model.ProfileImportDTO;
import com.kona.kms.web.profile.model.RSAKeyModel;
import com.kona.kms.web.profile.model.SecretKeyModel;
import com.kona.kms.web.profile.view.ProfileExcelView;
import com.kona.kms.web.ss.user.model.KmsUserModel;
import com.kona.kms.web.utils.AjaxResponse;
import com.kona.kms.web.utils.AjaxStatus;
import com.kona.kms.web.utils.GridPageData;
import com.kona.kms.web.utils.ProfileIDAttr;

@Controller
@RequestMapping("/KeyProfile")
public class KeyProfileController {
	
	private static final Logger logger = LoggerFactory.getLogger(KeyProfileController.class);

	@Autowired
	private KeyProfileBO keyProfileBO;
		
	@Autowired
	private ProfileExcelView profileExcelView;
		
	public KeyProfileController(){
	}
	
	@RequestMapping(value ="/search")
	public String search(Model model){		
						
		return "/profile/key_profile_list";
	}
	
	@RequestMapping(value="/searchList", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody GridPageData<KeyProfileModel> searchList(@ModelAttribute KeyProfileModel condition) throws Exception{
		
		GridPageData<KeyProfileModel> values = keyProfileBO.getKeyProfileList(condition);
		
		return values;
	}
	

	@RequestMapping(value="/popup/generateProfileID", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse generateKeyProfileID(@RequestParam("companyID") String companyID) throws Exception{
		
		
		String profileID = null;
		try{
			profileID =	keyProfileBO.generateKeyProfileID(companyID);
		}catch(Exception e){

			logger.error(e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode());
		}
		
		AjaxResponse res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode());
		res.setProfileID(profileID);
		
		return res;
	}
	
	@RequestMapping(value="/popup/generate")
	public ModelAndView keyProfileNewView(HttpServletRequest req) throws Exception{
					
		ModelAndView mv = new ModelAndView("/profile/key_profile_new");
		
		KmsUserModel user = (KmsUserModel)req.getSession().getAttribute("user");
		
		if(user.getUserRoleCode().equals("Key Manager")){
			mv.addObject("profileID", keyProfileBO.generateKeyProfileID(user.getCompanyId()));
			
		}
		return mv;
	}
	
	
	@RequestMapping(value="/generateProfile", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse insertKeyProfile(@ModelAttribute KeyProfileModel model, @ModelAttribute KeyProfileModel1 model1, Principal principal, HttpServletRequest req) throws Exception{
			
		Date now = new Date();
		
		//add by shifei 2014-8-21
//		SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
//		String nowStr = formatterDate.format(now);		
//		now = formatterDate.parse(nowStr);
		
		
		model.setRegistrationDate(now);
		model.setRegistrationUserID(principal.getName());
		model.setUpdateDate(now);
		model.setUpdateUserID(principal.getName());
		
		boolean isSecretKey = model.isSecretKeyProfile();
		
		ProfileIDAttr profileAttr = null;
		
		try{
			if(isSecretKey){
				profileAttr = keyProfileBO.insertKeyProfile(model);
			}else{
				profileAttr = keyProfileBO.insertPairKeyProfile(model, model1);
			}
			
		}catch(KmsException e){
			logger.error("KeyProfileController: insertKeyProfile: " + e.getMessage());
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(e.getMsgcode(), new Locale((String)req.getSession().getAttribute("lan"))));

		}
		catch(Exception e){
			logger.error("KeyProfileController: insertKeyProfile: " + e.getMessage());
						
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_CREATE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		AjaxResponse res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_CREATE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
		
		if(model.isClientDownloadable()){
			res.setProfileAttr(profileAttr);
		}
				
		return res;
	}
	
	@RequestMapping(value="/downloadProfile", method=RequestMethod.GET)
	public String downloadProfile(HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		String tokenID = req.getParameter("tokenID");
		
		if(tokenID != null){
			Cookie cookie = new Cookie("downloadToken", tokenID);    
			cookie.setMaxAge(30*60);
			cookie.setPath("/");
			res.addCookie(cookie);
		}		
		
		String id = req.getParameter("id");
		String version = req.getParameter("version");	
		
		String id1 = req.getParameter("id1");
		String version1 = req.getParameter("version1");	
			
		KeyProfileModel model = keyProfileBO.getKeyProfile(id, version);
		
		String filename = new StringBuffer().append("key_profile_").append(id).append(".xml").toString();
		
		res.setContentType("text/plain");
		res.setHeader("Content-Disposition", "attachment;filename="+filename);
		
		byte[] bytes = model.getKeyProfileXMLFile().getBytes();		
		
		ServletOutputStream out = res.getOutputStream();
		out.write(bytes);
		out.flush();		
		
		if(!StringUtils.isEmpty(id1) && !StringUtils.isEmpty(version1)){

			model = keyProfileBO.getKeyProfile(id1, version1);
				
				filename = new StringBuffer().append("key_profile_").append(id).append(".xml").toString();
				
				res.setContentType("text/plain");
				res.setHeader("Content-Disposition", "attachment;filename="+filename);
				
				bytes = model.getKeyProfileXMLFile().getBytes();		
				
				out = res.getOutputStream();
				out.write(bytes);
				out.flush();
		}
		
		out.close();
		
		
		return null;
	}
	
	@RequestMapping(value="/popup/edit")
	public ModelAndView keyProfileEditView(@RequestParam("keyProfileID") String keyProfileID, @RequestParam("keyProfileVersion") String keyProfileVersion) throws Exception{
		
		ModelAndView mv = new ModelAndView("/profile/key_profile_edit");
		
		KeyProfileModel model = keyProfileBO.getKeyProfile(keyProfileID, keyProfileVersion);
		
		if(model.getKeyTypeCode().equals("SECRET")){
			mv.addObject("profile", model);
			
			return mv;
		}else{
			
			List<KeyProfileModel> models = keyProfileBO.getPairKeyProfiles(model.getKeySubject());
			
			KeyProfileModel priModel = null;
			KeyProfileModel pubModel = null;
			
			for(KeyProfileModel model0 : models){
				if(model0.getKeyTypeCode().equals("PRIVATE")){
					priModel = model0;
				}else{
					pubModel = model0;
				}
			}			
						
			mv.setViewName("/profile/key_profile_edit_pair");
			mv.addObject("profile", priModel);
			mv.addObject("profile1", pubModel);
			
			return mv;
		}		
	}
	
	@RequestMapping(value="/editProfile", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse updateKeyProfile(@ModelAttribute KeyProfileModel model, Principal principal, HttpServletRequest req) throws Exception{		
		
		model.setUpdateUserID(principal.getName());
		model.setUpdateDate(new Date());
								
		try{
			keyProfileBO.updateKeyProfile(model);
		}catch(Exception e){
			logger.error(e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_EDIT_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		AjaxResponse res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_EDIT_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
		
		ProfileIDAttr attr = new ProfileIDAttr(false,model.getKeyProfileID(),model.getKeyProfileVersion(),null,null);
		
		res.setProfileAttr(attr);
		
		return res;
	}
	
	@RequestMapping(value="/editPairProfile", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse updateKeyProfile(@ModelAttribute KeyProfileModel priModel, @ModelAttribute KeyProfileModel1 pubModel, Principal principal, HttpServletRequest req) throws Exception{		
		
		priModel.setUpdateUserID(principal.getName());
		priModel.setUpdateDate(new Date());
		
		logger.debug(">>>>>>>>>>>>>>>>>  priModel " + priModel);
		logger.debug(">>>>>>>>>>>>>>>>>  pubModel " + pubModel);
		
		try{
			keyProfileBO.updatePairKeyProfile(priModel, pubModel);
		}catch(Exception e){
			logger.error("KeyProfileController: updatePairKeyProfile: " + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_EDIT_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		AjaxResponse res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_EDIT_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
		
		ProfileIDAttr attr = new ProfileIDAttr(false,priModel.getKeyProfileID(),priModel.getKeyProfileVersion(),null,null);
		
		res.setProfileAttr(attr);
		
		return res;
	}
	
	@RequestMapping(value="/popup/import")
	public ModelAndView keyProfileImportView() throws Exception{
		ModelAndView mv = new ModelAndView("/profile/key_profile_import");
		
		return mv;
	}
	
	@RequestMapping(value="/popup/cancelImport")
	public @ResponseBody AjaxResponse cancelImport(HttpServletRequest req, @RequestParam("tokenID") String tokenID, String pubTokenID) throws Exception{
		
		HttpSession session = req.getSession();
		
		session.removeAttribute(tokenID);
		
		if(pubTokenID != null) session.removeAttribute(pubTokenID);
		
		return  new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode());
	}
	
	@RequestMapping(value="/popup/importPairProfile", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse importProfile(HttpServletRequest req, @ModelAttribute KeyProfileModel priModel, @ModelAttribute KeyProfileModel1 pubModel, @RequestParam("tokenID") String tokenID, @RequestParam("pubTokenID") String pubTokenID, Principal principal) throws Exception{
		
		Date now = new Date();
		priModel.setRegistrationDate(now);
		priModel.setRegistrationUserID(principal.getName());
		priModel.setUpdateDate(now);
		priModel.setUpdateUserID(principal.getName());
		
		HttpSession session = req.getSession();
				
		KeyProfileModel priKey = (KeyProfileModel) session.getAttribute(tokenID);
		
		priModel.setKeyValue(priKey.getKeyValue());
		priModel.setTransportKey(priKey.getTransportKey());
		
		KeyProfileModel pubModel0 = ConvertUtil.createPublicKeyProfileModel(priModel, pubModel);
		
		KeyProfileModel pubKey = (KeyProfileModel) session.getAttribute(pubTokenID);
		
		pubModel0.setKeyValue(pubKey.getKeyValue());
		pubModel0.setTransportKey(pubKey.getTransportKey());
		
		logger.debug("######################## pri: " + priModel + ":" + priModel.getKeyValue());
		logger.debug("######################## pub: " + pubModel0 + ":" + pubModel0.getKeyValue());
		
		try{
			keyProfileBO.importPairKeyProfile(priModel, pubModel0);
			
			session.removeAttribute(tokenID);
			session.removeAttribute(pubTokenID);
		}catch(KmsException e){
			logger.error("KeyProfileController: importProfile: " + e.getMessage());
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(e.getMsgcode(), new Locale((String)req.getSession().getAttribute("lan"))));

		}
		catch(Exception e){
			logger.error("KeyProfileController: importProfile: " + e.getMessage());
			logger.error(e.getMessage());
						
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_IMPORT_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		AjaxResponse res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_IMPORT_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
						
		return res;
	}
	
	@RequestMapping(value="/popup/importProfile", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse importProfile(HttpServletRequest req, @ModelAttribute KeyProfileModel model, @RequestParam("tokenID") String tokenID, Principal principal) throws Exception{
		
		Date now = new Date();
		model.setRegistrationDate(now);
		model.setRegistrationUserID(principal.getName());
		model.setUpdateDate(now);
		model.setUpdateUserID(principal.getName());
		
		HttpSession session = req.getSession();
				
		KeyProfileModel key = (KeyProfileModel) session.getAttribute(tokenID);
		
		model.setKeyValue(key.getKeyValue());
		model.setTransportKey(key.getTransportKey());
				
		try{
			keyProfileBO.importKeyProfile(model);
			
			session.removeAttribute(tokenID);
		}catch(KmsException e){
			logger.error("KeyProfileController: importProfile: " + e.getMessage());
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(e.getMsgcode(), new Locale((String)req.getSession().getAttribute("lan"))));

		}
		catch(Exception e){
			logger.error("KeyProfileController: importProfile: " + e.getMessage());
			logger.error(e.getMessage());
						
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_IMPORT_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		AjaxResponse res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_IMPORT_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
						
		return res;
	}
	
	@RequestMapping(value="/popup/parseProfile", method=RequestMethod.POST)
	public ModelAndView parseProfile(HttpServletRequest req, @ModelAttribute("profileImportDTO") ProfileImportDTO file, Principal principal) throws Exception{
		
		ModelAndView mv = new ModelAndView("/profile/key_profile_import_edit");
		
		/**
		 * 1. File Validation 
		 */
		if(file.isEmpty()){
			
			String message = MsgManager.getInstance().getMessage(KMSCode.KEY_IMPORT_FILE_EMPTY, new Locale((String)req.getSession().getAttribute("lan")));
			logger.info("KeyProfileController: parseProfile: " + message);
			mv.addObject("message", message);
			mv.setViewName("/profile/key_profile_import");
			return mv;
		}
		
		if(file.isPairKeyImport() && file.isPubEmpty()){
			String message = MsgManager.getInstance().getMessage(KMSCode.KEY_IMPORT_FILE_EMPTY, new Locale((String)req.getSession().getAttribute("lan")));
			logger.info("KeyProfileController: parseProfile: " + message);
			mv.addObject("message", message);
			mv.setViewName("/profile/key_profile_import");
			return mv;
		}				
		
		HttpSession session = req.getSession();		
		KmsUserModel user = (KmsUserModel)session.getAttribute("user");
		
		/**
		 * 2.Unmarshal xml file.
		 */		
		KeyProfile key = null;
		
		try{
			key = GpMsgBinder.getInstance().unmarshal(file.getFile().getInputStream(), "KeyProfile", KeyProfile.class);
		}catch(Exception e){			
			logger.error("KeyProfileController: parseProfile: unmarshaling key profile: " + e.getMessage());
			mv.addObject("message", MsgManager.getInstance().getMessage(KMSCode.KEY_IMPORT_FILE_NOTSUPPORTED, new Locale((String)req.getSession().getAttribute("lan"))));
			mv.setViewName("/profile/key_profile_import");
			return mv;
		}
		
		/**
		 * 3. KeyProfileModel Creation with KeyProfile
		 */
		KeyProfileModel model = null;
		try {
			model = ConvertUtil.createKeyProfileModel(key, principal.getName());		
			
			if(user.getUserRoleCode().equals("Key Manager")){
				model.setCompanyID(user.getCompanyId());
				model.setCompanyName(user.getCompanyName());
				model.setTokenLabel(user.getTokenLabel());
			}
		} catch (Exception e) {			
			logger.error("KeyProfileController: parseProfile: converting key profile model: " + e.getMessage());
			
			mv.addObject("message", MsgManager.getInstance().getMessage(KMSCode.KEY_IMPORT_FILE_NOTSUPPORTED, new Locale((String)req.getSession().getAttribute("lan"))));
			mv.setViewName("/profile/key_profile_import");
			return mv;
		}		
		
		/**
		 * 4. Validation of Key Type Code
		 */
		if(!file.isPairKeyImport()){
			if(!model.getKeyTypeCode().equals("SECRET")){
				String message = MsgManager.getInstance().getMessage(KMSCode.KEY_IMPORT_NOT_SECRET, new Locale((String)req.getSession().getAttribute("lan")));
				logger.info("KeyProfileController: parseProfile: validation: " + message);
				mv.addObject("message", message);
				mv.setViewName("/profile/key_profile_import");
				return mv;
			}
		}
		
		/*
		 * 5. In case of Pair Key Import, repeat 2 and 3
		 */
		if(file.isPairKeyImport()){
			KeyProfile pubKey = null;
			try{
				pubKey = GpMsgBinder.getInstance().unmarshal(file.getPubFile().getInputStream(), "KeyProfile", KeyProfile.class);
			}catch(Exception e){			
				logger.error("KeyProfileController: parsePublicKeyProfile: unmarshaling key profile: " + e.getMessage());
				mv.addObject("message", MsgManager.getInstance().getMessage(KMSCode.KEY_IMPORT_FILE_NOTSUPPORTED, new Locale((String)req.getSession().getAttribute("lan"))));
				mv.setViewName("/profile/key_profile_import");
				return mv;
			}
			
			KeyProfileModel pubModel = null;
			try {
				pubModel = ConvertUtil.createKeyProfileModel(pubKey, principal.getName());
				
			} catch (Exception e) {			
				logger.error("KeyProfileController: parsePublicKeyProfile: converting key profile model: " + e.getMessage());
				
				mv.addObject("message", MsgManager.getInstance().getMessage(KMSCode.KEY_IMPORT_FILE_NOTSUPPORTED, new Locale((String)req.getSession().getAttribute("lan"))));
				mv.setViewName("/profile/key_profile_import");
				return mv;
			}	
			
			if(!model.getKeyTypeCode().equals("PRIVATE")){
				String message = MsgManager.getInstance().getMessage(KMSCode.KEY_IMPORT_NOT_PRIVATE, new Locale((String)req.getSession().getAttribute("lan")));
				logger.info("KeyProfileController: parsePublicKeyProfile: validation: " + message);
				mv.addObject("message", message);
				mv.setViewName("/profile/key_profile_import");
				return mv;
			}
			
			if(!pubModel.getKeyTypeCode().equals("PUBLIC")){
				String message = MsgManager.getInstance().getMessage(KMSCode.KEY_IMPORT_NOT_PUBLIC, new Locale((String)req.getSession().getAttribute("lan")));
				logger.info("KeyProfileController: parsePublicKeyProfile: validation: " + message);
				mv.addObject("message", message);
				return mv;
			}
			
			String tokenID = UUID.randomUUID().toString();
			
			session.setAttribute(tokenID, pubModel);
			
			mv.addObject("pubTokenID", tokenID);
			mv.addObject("profile1", pubModel);
			
			mv.setViewName("/profile/key_profile_import_edit_pair");			
		}		
		
		String tokenID = UUID.randomUUID().toString();
		
		session.setAttribute(tokenID, model);
		
		mv.addObject("tokenID", tokenID);
		mv.addObject("profile", model);
		
		return mv;
	}
	
	@RequestMapping(value="/popup/export", method=RequestMethod.POST)
	public ModelAndView keyProfileExportView(@RequestParam("keyProfileID") String keyProfileID, @RequestParam("keyProfileVersion") String keyProfileVersion) throws Exception{
		ModelAndView mv = new ModelAndView("/profile/key_profile_export");
		
		mv.addObject("profile", keyProfileBO.getKeyProfile(keyProfileID, keyProfileVersion));
		
		return mv;
	}
	
	@RequestMapping(value="/popup/exportProfile", method=RequestMethod.POST)
	public String exportKeyProfile(HttpServletRequest req, HttpServletResponse res, @ModelAttribute ProfileExportDTO dto) throws Exception{
				
		try{
			keyProfileBO.exportKeyProfile(dto);
		}catch(KmsException e){
			
			//return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(e.getMsgcode()));

		}catch(Exception e){

			//return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_EXPORT_FAIL));
		}		
		
		String filename = new StringBuffer().append("key_profile_").append(dto.getKeyProfileID()).append(".xml").toString();
		
		res.setContentType("text/plain");
		res.setHeader("Content-Disposition", "attachment;filename="+filename);
		
		byte[] bytes = dto.getExportXmlFile().getBytes();		
		
		ServletOutputStream out = res.getOutputStream();
		out.write(bytes);
		out.flush();
		out.close();	
		
		return null;
		
		//return new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_EXPORT_SUCCESS));
	}	
	
	@RequestMapping(value="/popup/secretKeyValue", method=RequestMethod.POST)
	public ModelAndView secretKeyValueView(@RequestParam("keyProfileID") String keyProfileID, @RequestParam("keyProfileVersion") String keyProfileVersion,  @RequestParam("keyLabel") String keyLabel, @RequestParam("keySubtypeCode") String keySubtypeCode, @RequestParam("keySize") int keySize) throws Exception{
		ModelAndView mv = new ModelAndView("/profile/key_value_secret");
				
		mv.addObject("profileID", keyProfileID);
		mv.addObject("profileVersion", keyProfileVersion);
		mv.addObject("keyLabel", keyLabel);
		mv.addObject("keySubtypeCode", keySubtypeCode);
		mv.addObject("keySize", keySize);
		
		return mv;
	}
	
	@RequestMapping(value="/popup/setSecretKeyValue", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse setSecretKeyValue(@ModelAttribute KeyValueDTO dto, HttpServletRequest req) throws Exception{
			
		AjaxResponse res = null;
		try{
			keyProfileBO.setSecretKeyValue(dto);
		}catch(KmsException e){
			logger.error(e.getMessage());
			
			res = new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(e.getMsgcode(), new Locale((String)req.getSession().getAttribute("lan"))));
			
			return res;
		}
		catch(Exception e){			
			
			logger.error("KeyProfileController: setSecretKeyValue: " + e.getMessage());
			
			res = new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_VALUE_CREATE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
			
			return res;
		}
	
		res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_VALUE_CREATE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
		res.setKeyValue(dto);
		
		return res;
		
	}
	
	@RequestMapping(value="/popup/getSecretKeyKCV", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse getSecretKeyKCV(@RequestParam("keySubtype") String mech, @RequestParam("keySize") int keySize, @RequestParam("component") String text, HttpServletRequest req) throws Exception{
			
		
		AjaxResponse res = null;
		String kcv = null;
		try{
			kcv = keyProfileBO.getSecretKeyKCV(mech, keySize, text);
		}catch(KmsException e){
			logger.error(e.getMessage());
			
			res = new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(e.getMsgcode(), new Locale((String)req.getSession().getAttribute("lan"))));
			
			return res;
		}
		catch(Exception e){			
			
			logger.error("KeyProfileController: setSecretKeyValue: " + e.getMessage());
			
			res = new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_KCV_CREATE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
			
			return res;
		}
	
		res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_KCV_CREATE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
		res.setKcv(kcv);
		
		return res;
	}
		
	
	@RequestMapping(value="/popup/pairKeyValue", method=RequestMethod.POST)
	public ModelAndView pairKeyValueView(@RequestParam("keySubject") String keySubject, @RequestParam("keyTypeCode") String keyTypeCode, @RequestParam("keySubtypeCode") String keySubtypeCode, @RequestParam("keyLabel") String keyLabel, @RequestParam("keySize") int keySize) throws Exception{
		ModelAndView mv = new ModelAndView("/profile/key_value_rsa");
		
		mv.addObject("keySubject", keySubject);
		mv.addObject("keyTypeCode", keyTypeCode);
		mv.addObject("keySubtypeCode", keySubtypeCode);
		mv.addObject("keyLabel", keyLabel);
		mv.addObject("keySize", keySize);
		
		return mv;
	}
	
	@RequestMapping(value="/popup/setPairKeyValue", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse setPairKeyValue(@ModelAttribute KeyValueDTO dto, HttpServletRequest req) throws Exception{
			
		AjaxResponse res = null;
		try{
			keyProfileBO.setPairKeyValue(dto);
		}catch(Exception e){
						
			logger.error("KeyProfileController: setPairKeyValue: " + e.getMessage());
			
			res = new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_VALUE_CREATE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
			
			return res;
		}
	
		res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_VALUE_CREATE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
				
		return res;		
	}
	
	@RequestMapping(value="/deleteProfile", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse deleteKeyProfile(@RequestParam("profileID") String[] profileID, Principal principal, HttpServletRequest req) throws Exception{
		
		try{
			keyProfileBO.deleteKeyProfile(profileID, principal.getName());
		}catch(Exception e){
						
			logger.error("KeyProfileController: deleteKeyProfile: " + e.getMessage());
			
			return new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_DELETE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		return new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_DELETE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
	}
	
	@RequestMapping(value="/popup/detail")
	public ModelAndView getKeyProfileDetail(@RequestParam("keyProfileID") String keyProfileID, @RequestParam("keyProfileVersion") String keyProfileVersion) throws Exception{
		
		ModelAndView mv = new ModelAndView("/profile/key_profile_detail");
				
		KeyProfileModel value = keyProfileBO.getKeyProfile(keyProfileID, keyProfileVersion);
		
		mv.addObject("profile", value);
		
		return mv;				
	}
	
	@RequestMapping(value="/json/detail")
	public @ResponseBody PairKeyDTO getKeyProfileDetailByJson(@RequestParam("keySubject") String keySubject) throws Exception{
		
		List<KeyProfileModel> profiles = keyProfileBO.getPairKeyProfiles(keySubject);
		
		return new PairKeyDTO(profiles);
						
		//return keyProfileBO.getKeyProfile(keyProfileID, keyProfileVersion);
					
	}
	
	@RequestMapping(value="/excel", method=RequestMethod.POST)
	public ModelAndView excel(@ModelAttribute KeyProfileModel condition) throws Exception{
		
		ModelAndView mv = new ModelAndView(this.profileExcelView);
		
		
		mv.addObject("list", keyProfileBO.getKeyProfileListAll(condition));
		
		return mv;
	}
	
	
	
	
	//add by shifei 20140603
	@RequestMapping(value = "/popup/autoGen", method = RequestMethod.POST)
	public @ResponseBody
	RSAKeyModel autoGen(@RequestParam("keySize") int keySize, @RequestParam("exp") String exp) throws Exception {
		RSAKeyModel rsaKeyModel = null;
		
		if (exp.equals("") || exp.equals(null)){
			exp = "0";
		}
		
		int expInt = Integer.parseInt(exp);
		
		try{
			rsaKeyModel = ConvertUtil.autoGenRSA(keySize, expInt);
		} catch (Exception e){
			logger.error(e.getMessage());
			rsaKeyModel.setStatus("FAILURE");
			return rsaKeyModel;
		}
		
		rsaKeyModel.setStatus("SUCCESS");
	
		
		return rsaKeyModel;
	
	}
	
	//add by shifei 20140602
	@RequestMapping(value = "/popup/autoGenSecret", method = RequestMethod.POST)
	public @ResponseBody
	SecretKeyModel autoGenSecret(@RequestParam("keySize") int keySize, @RequestParam("keySubtype") String keySubtype, @RequestParam("org_key") String org_key) throws Exception {		
		SecretKeyModel secretKeyModel = null;
		
		try{
			if (keySubtype.equalsIgnoreCase("DES")){
				org_key = ConvertUtil.oddCheckDES(org_key);
//				System.out.println("<<<<<<<<<<<<<<<<<<<<oddCheckDES");
			}
			
			secretKeyModel = ConvertUtil.autoGenSecret(keySize, keySubtype, org_key);
			
		} catch (Exception e){
			logger.error(e.getMessage());
			secretKeyModel.setStatus("FAILURE");
			return secretKeyModel;
		}
		
		secretKeyModel.setStatus("SUCCESS");
	
		return secretKeyModel;
	
	}
	
	

	@RequestMapping(value="/sendKeyProfile", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse sendKeyProfile(@RequestParam("keyProfileID") String keyProfileID, @RequestParam("keyProfileVersion") String keyProfileVersion, @RequestParam("keyValueFlag") String keyValueFlag, Principal principal, HttpServletRequest req) throws Exception{
		AjaxResponse res = null;
		try{
			//modify by shifei 20140822
//			if(keyValueFlag.equalsIgnoreCase("Y")){
//				keyProfileBO.sendKeyProfile(keyProfileID, keyProfileVersion, principal.getName());
//			}
			keyProfileBO.sendKeyProfile(keyProfileID, keyProfileVersion, principal.getName());
			
		}catch(Exception e){
						
			logger.error("KeyProfileController: sendKeyProfile: " + e.getMessage());
			
			res = new AjaxResponse(AjaxStatus.FAILURE.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_DELETE_FAIL, new Locale((String)req.getSession().getAttribute("lan"))));
		}
		
		res = new AjaxResponse(AjaxStatus.SUCCESS.getStatusCode(), MsgManager.getInstance().getMessage(KMSCode.KEY_DELETE_SUCCESS, new Locale((String)req.getSession().getAttribute("lan"))));
		return res;
	}
	
	
	@ExceptionHandler(Exception.class)
	public void handleException(Exception e){
		logger.error("KeyProfileController: exceptionHandler: " + e.getMessage());	
		
	}
}
