package com.kona.kms.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kona.kms.KMSCode;
import com.kona.kms.framework.MsgManager;
import com.kona.kms.token.KeyManager;
import com.kona.kms.web.ss.user.bo.KmsUserBO;
import com.kona.kms.web.ss.user.model.KmsUserModel;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private KmsUserBO kmsUserBO;
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest req,  Principal principal){
		
		logger.info("LoginController: login: [UserID]:  " + principal.getName());
		
		KmsUserModel user = kmsUserBO.getKmsUser(principal.getName());
		
		req.getSession().setAttribute("user", user);
		
		ModelAndView mv = new ModelAndView();
		
		if(KeyManager.getInstance().isInit()){
			mv.setViewName("redirect:/KeyProfile/search.do");
		}else{
			mv.setViewName("redirect:/HsmSetting/search.do");
		}
		
		return mv;
		
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public ModelAndView loginFailed(ModelMap map) {		
		ModelAndView mv = new ModelAndView("home");
		
		mv.addObject("message", MsgManager.getInstance().getMessage(KMSCode.LOGIN_FAILED));
		
		return mv;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logout(ModelMap map, Principal principal){
		
		if(principal != null){
			logger.info("LoginController: logout: [UserID]: " + principal.getName());
		}else{
			return new ModelAndView("redirect:/");
		}
		
		ModelAndView mv = new ModelAndView("forward:/");
		
		mv.addObject("message", MsgManager.getInstance().getMessage(KMSCode.LOGOUT_SUCCESS));
		
		return mv;		
	}
}
