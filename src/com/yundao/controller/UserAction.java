package com.yundao.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.entity.User;
import com.yundao.service.IUserService;

@Controller
@RequestMapping("/yundao")
public class UserAction extends BaseAction {
	
	@Autowired
	IUserService userService;
	
	private Logger log = Logger.getLogger(getClass());
	
	/**
	 * 
	 * author:                  史群峰 <br />
	 * Created:                 2016-4-26 上午9:39:12 <br />
	 * <font color="red">Description:             获取数据库当前时间字符串</font> <br />
	 * 
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("getCurrentTime.action")
	public User getCurrentTime(){
		User user = userService.getCurrentTime();
		log.info("user=" + user);
		return user;
	}
}
