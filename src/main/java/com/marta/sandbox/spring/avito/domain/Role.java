package com.marta.sandbox.spring.avito.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="role")
public class Role {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="role", fetch=FetchType.LAZY)
	List<User> users;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}

}
