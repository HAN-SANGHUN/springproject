package com.kona.kms.web;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7480222961478351384L;

	private String companyID;
	
	private String companyName;
	
	
	public CustomUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}


	public String getCompanyID() {
		return companyID;
	}


	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
