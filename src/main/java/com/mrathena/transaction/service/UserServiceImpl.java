package com.mrathena.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mrathena.transaction.dao.UserMapper;
import com.mrathena.transaction.entity.User;
import com.mrathena.transaction.tool.ThreadKit;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	private void log(String message) {
		System.out.println(ThreadKit.getName() + ": " + message);
	}

	@Override
	public boolean register(User user) throws Exception {
		String username = user.getUsername();
		log("开始查询用户[" + username + "]");
		int count = userMapper.selectCountByUsername(username);
		ThreadKit.sleep(1000);// 模拟查询时间, 创造并发问题发生的可能性
		if (count == 0) {
			log("用户名[" + username + "]不存在, 可插入");
			int flag = userMapper.insertSelective(user);
			log("用户插入" + (flag != 0 ? "成功" : "失败"));
			return flag != 0;
		}
		log("用户名[" + username + "]已存在, 不可重复插入");
		return false;
	}

	@Override
	public synchronized boolean register2(User user) throws Exception {
		String username = user.getUsername();
		log("开始查询用户[" + username + "]");
		int count = userMapper.selectCountByUsername(username);
		ThreadKit.sleep(1000);// 模拟查询时间, 创造并发问题发生的可能性
		if (count == 0) {
			log("用户名[" + username + "]不存在, 可插入");
			int flag = userMapper.insertSelective(user);
			log("用户插入" + (flag != 0 ? "成功" : "失败"));
			return flag != 0;
		}
		log("用户名[" + username + "]已存在, 不可重复插入");
		return false;
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public boolean register3(User user) throws Exception {
		String username = user.getUsername();
		log("开始查询用户[" + username + "]");
		int count = userMapper.selectCountByUsername(username);
		ThreadKit.sleep(1000);// 模拟查询时间, 创造并发问题发生的可能性
		if (count == 0) {
			log("用户名[" + username + "]不存在, 可插入");
			int flag = userMapper.insertSelective(user);
			log("用户插入" + (flag != 0 ? "成功" : "失败"));
			return flag != 0;
		}
		log("用户名[" + username + "]已存在, 不可重复插入");
		return false;
	}

}
