package com.caeme.activityreport.core.service;

import java.util.List;

import com.caeme.activityreport.core.model.User;

public interface UserService {
	
	int saveUser(User user);
	 
    List<User> findAllUsers();
 
    void deleteUser(Integer id);
 
    User findById(Integer id);
 
    void updateUser(User user);

}
