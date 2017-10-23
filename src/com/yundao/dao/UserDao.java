package com.yundao.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yundao.entity.User;

@Repository("userDao")
public class UserDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 
	 * author:                  史群峰 <br />
	 * Created:                 2016-4-26 上午9:36:47 <br />
	 * <font color="red">Description:             获取数据库当前时间字符串</font> <br />
	 * 
	 * @return User
	 */
	public User getCurrentTime() {
		return sqlSessionTemplate.selectOne("com.yundao.mapper.user.getCurrentTime");
	}
}
