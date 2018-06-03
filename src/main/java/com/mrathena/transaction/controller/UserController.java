package com.mrathena.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrathena.transaction.entity.User;
import com.mrathena.transaction.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping("registe2")
	public void registe2() throws Exception {
		service.register2(new User().withUsername("mrathena"));
	}

	@RequestMapping("registe3")
	public void registe3() throws Exception {
		service.register3(new User().withUsername("mrathena"));
	}

}