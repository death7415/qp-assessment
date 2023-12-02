package com.qpassessment.qpassessment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFactory {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	public AdminService getAdminService() {
		return adminService;
	}
	
	public UserService getUserService() {
		return userService;
	}
}
