package com.example.demo.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.config")
public class AuthSecuredProperties {
	private String adminRole;
	private String guestRole;
	private String userRole;
	private String adminUserName;
	private String adminPassword;
	private String apiUserName;
	private String apiPassword;
	private String guestUserName;
	private String guestPassword;
	
	public String getAdminRole() {		
		return adminRole;
	}
	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}
	public String getGuestRole() {
		return guestRole;
	}
	public void setGuestRole(String guestRole) {
		this.guestRole = guestRole;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getAdminUserName() {
		return adminUserName;
	}
	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getApiUserName() {
		return apiUserName;
	}
	public void setApiUserName(String apiUserName) {
		this.apiUserName = apiUserName;
	}
	public String getApiPassword() {
		return apiPassword;
	}
	public void setApiPassword(String apiPassword) {
		this.apiPassword = apiPassword;
	}
	public String getGuestUserName() {
		return guestUserName;
	}
	public void setGuestUserName(String guestUserName) {
		this.guestUserName = guestUserName;
	}
	public String getGuestPassword() {
		return guestPassword;
	}
	public void setGuestPassword(String guestPassword) {
		this.guestPassword = guestPassword;
	}
	
		
}
