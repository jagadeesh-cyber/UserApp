package com.upgrad.userApp.service;

import com.upgrad.userApp.dao.UserDao;
import com.upgrad.userApp.entities.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User createUser(User user) {
		return userDao.save(user);
	}

	@Override
	public User getUserBasedOnId(int id) {

		return userDao.findById(id).get();
	}

	@Override
	public User updateUser(User user) {
		User storedUser = getUserBasedOnId(user.getUserId());
		storedUser.setDateOfBirth(user.getDateOfBirth());
		storedUser.setFirstName(user.getFirstName());
		storedUser.setLastName(user.getLastName());
		storedUser.setPassword(user.getPassword());
		storedUser.setPhoneNumbers(user.getPhoneNumbers());
		storedUser.setUserId(user.getUserId());
		storedUser.setUsername(user.getUsername());
		return userDao.save(storedUser);
	}

	@Override
	public User deleteUser(User user) {
		userDao.delete(user);
		return null;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}
}
