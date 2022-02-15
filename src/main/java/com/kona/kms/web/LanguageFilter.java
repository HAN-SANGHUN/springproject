package com.kona.kms.web;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class LanguageFilter extends UsernamePasswordAuthenticationFilter{

	private static final Logger logger = LoggerFactory.getLogger(LanguageFilter.class);
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
		
		String lan = request.getParameter("lan");
		
		logger.debug("LanguageFilter: attemptAuthentication: Language: " + lan);
		
		request.getSession().setAttribute("lan", lan);
		
		return super.attemptAuthentication(request, response);
	}
}
