package com.zent.dao;

import java.util.List;

import com.zent.entity.User;

public interface IUserDAO {
	public User checkLogin(User user);
	public void changeProfile(User user, Integer id);
	public void add(User user);
	public Integer countUserByUserName(String username);
	public List<User> getAll(User user, Integer page);
	public Integer countAll(User user);
	public void delete(Integer id);
}
