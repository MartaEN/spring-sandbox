package com.marta.sandbox.spring.avito.service;

import com.marta.sandbox.spring.avito.domain.User;

import java.util.List;

public interface UserService {
	
	
	User get(Long id);
	
	User getByLogin(String login);
	
	List<User> getAll();
	
	void save(User user);
	
	void remove(User user);
	

}
