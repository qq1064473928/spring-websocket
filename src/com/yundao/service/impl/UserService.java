package com.yundao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.dao.UserDao;
import com.yundao.entity.User;
import com.yundao.service.IUserService;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	UserDao userDao;

	//@Transactional
	@Override
	public User getCurrentTime() {
		return userDao.getCurrentTime();
	}

}
