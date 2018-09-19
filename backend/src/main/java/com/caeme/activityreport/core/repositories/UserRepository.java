package com.caeme.activityreport.core.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.caeme.activityreport.core.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	List<User> findAllByOrderByNameAsc();
	
}
