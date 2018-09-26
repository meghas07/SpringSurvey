package com.zycus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.entity.User;

@Service
public class UserService {

	@Autowired
	private Validator loginValidation;

	private User validUser;

	public String login(User user) {

		validUser = loginValidation.checkLoginCredentials(user);
		if (validUser.getRole().equalsIgnoreCase("administrator"))
			return "admin";
		else if (validUser.getRole().equalsIgnoreCase("user"))
			return "user";
		return null;

	}

}
