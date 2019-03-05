package com.catherine.service;
import com.catherine.bean.User;
import com.catherine.dao.UserDao;
import com.catherine.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {
//	private UserDao ud=new UserDaoImpl();
	private UserDao ud;
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	@Override
	public User getUserByInfo(User u) {
		// TODO Auto-generated method stub
		return ud.getUserByInfo(u);
	}
}
