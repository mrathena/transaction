package com.mrathena.transaction.service;

import com.mrathena.transaction.entity.User;

public interface UserService {

	// 普通代码, 会有并发问题
	boolean register(User user) throws Exception;
	
	boolean register2(User user) throws Exception;
	
}