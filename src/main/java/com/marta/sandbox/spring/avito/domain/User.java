package com.marta.sandbox.spring.avito.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Pattern(regexp="[A-Z][a-zA-Z]*",message="{validation.author.firstname.pattern}")
	@Size(min=2, max=50, message="{validation.author.firstname.size}")
	@Column(name="first_name")
	private String firstname;
	
	@Pattern(regexp="[a-zA-z]+([ '-][a-zA-Z]+)*",message="{validation.author.lastname.pattern}")
	@Size(min=2, max=50, message="{validation.author.lastname.size}")
	@Column(name="last_name")
	private String lastname;
	
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="{validation.author.email.pattern}")
	@Column(name="email")
	private String email;
	
    @ManyToOne
    @JoinColumn(name="role_id")
	private Role role;
    
    @Pattern(regexp="^[a-zA-Z0-9._-]{3,}$", message="{validation.author.login.pattern}")
    @Column(name="login")
    private String login;
    
    @Pattern(regexp=".{8,}", message="{validation.author.password.pattern}")
    @Column(name="password")
    private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy="author", fetch=FetchType.LAZY)
	private List<Advertisement> advertisements;
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Advertisement> getAdvertisements() {
		return advertisements;
	}

	public void setAdvertisements(List<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
