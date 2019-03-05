package com.catherine.dao;

import com.catherine.bean.User;

public interface UserDao {
	//通过用户信息获取用户对象
	User getUserByInfo(User u);

}
