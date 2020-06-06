package com.example.demo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.example.demo.controller.ValidGroup1;
import com.example.demo.controller.ValidGroup2;
import com.example.demo.controller.ValidGroup3;

public class SignupForm {
	@NotEmpty(groups = ValidGroup1.class)
	private String userName;
	
	@NotEmpty(groups = ValidGroup1.class)
	private String userId;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min=4, max=32, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup3.class)
	private String password;
	
	@NotEmpty(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String email;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
 }
