package com.marta.sandbox.spring.avito.repository;

import com.marta.sandbox.spring.avito.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByLogin(String login);

}
