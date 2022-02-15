package com.kona.kms.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.kona.kms.web.ss.user.dao.KmsUserDao;
import com.kona.kms.web.ss.user.model.KmsUserModel;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	
	@Autowired
	private KmsUserDao kmsUserDao;
	
	private String defaultAdminID = "admin";
	
	private String defaultAdminPwd = "d033e22ae348aeb5660fc2140aec35850c4da997";
	
	private static boolean isAdminInit = false;
	
	@Value("${default_kms_manager_id}")
	public void setDefaultKmsManagerID(String defaultAdminID){
		this.defaultAdminID = StringUtils.trimWhitespace(defaultAdminID);
	}
	
	@Value("${default_kms_manager_pwd}")
	public void setKeyProfileSavePath(String defaultAdminPwd){
		this.defaultAdminPwd = StringUtils.trimWhitespace(defaultAdminPwd);
	}
	
	
	public static boolean isAdminInit(){
		return isAdminInit;
	}
	
	public static void setAdminInitialized(){
		isAdminInit = true;
	}
	
	public void init(){
		int count = kmsUserDao.checkKmsManagerRegistered();	
		
		if(count > 0){
			isAdminInit = true;
			
			logger.info("UserDetailServiceImpl: KMS Manager Initialized.");
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String id)
			throws UsernameNotFoundException {
		
		if(isAdminInit){
			KmsUserModel model = kmsUserDao.getKmsUser(id);
			
			if(model == null) {
				
				throw new UsernameNotFoundException("No user Found:[UserID]: " + id);
			}
			
			boolean enabled = model.getActiveStatusCode().equals("Active") ? true:false;
			
			List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			auths.add(new SimpleGrantedAuthority(model.getUserRoleCode()));
			
			UserDetails user = new User(id, model.getUserPwd(), enabled, true, true, true, auths);
			
			return user;
		}else{
			
			List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			auths.add(new SimpleGrantedAuthority("KMS Manager"));
			
			UserDetails user = new User(defaultAdminID, defaultAdminPwd, true, true, true, true, auths);
			
			return user;
		}
		
	}

}
