package com.marta.sandbox.spring.avito.repository;

import com.marta.sandbox.spring.avito.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long > {
	
	public Role findByName(String name);

}
