package com.marta.sandbox.spring.avito.service;

import com.marta.sandbox.spring.avito.domain.Role;
import com.marta.sandbox.spring.avito.domain.User;
import com.marta.sandbox.spring.avito.repository.RoleRepository;
import com.marta.sandbox.spring.avito.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	//имя роли по умолчанию
	private static final String DEFAULT_ROLE_NAME="user";

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	@Transactional(readOnly=true)
	public User get(Long id) {
	
		return userRepository.findOne(id);
	}
	
	

	@Override
	@Transactional(readOnly=true)
	public List<User> getAll() {

		return userRepository.findAll();
	}

	@Override
	@Transactional
	public void save(User user) {
        //поиск конкретной роли по имени
		Role role = roleRepository.findByName(DEFAULT_ROLE_NAME);
		//установка роли для автора
		user.setRole(role);
		//сохранения автора
		userRepository.save(user);
	}
	
	
	@Override
	@Transactional
	public void remove(User user) {

		userRepository.delete(user);
	}


	@Override
	@Transactional(readOnly=true)
	public User getByLogin(String login) {
		// TODO Auto-generated method stub
		User user = userRepository.findByLogin(login);
		return user;
	}

}
