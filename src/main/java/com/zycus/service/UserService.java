package com.zycus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.entity.User;
import com.zycus.myenums.UserRole;
import com.zycus.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public UserRole login(int userId, String password) {

		User user = (User) userRepository.checkUserLoginCredentials(userId);
		if (user.getPassword().equals(password))
			return user.getRole();
		return null;

	}

}
