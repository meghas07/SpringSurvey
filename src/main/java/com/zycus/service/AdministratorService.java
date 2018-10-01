package com.zycus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.entity.User;
import com.zycus.myenums.UserRole;
import com.zycus.repository.UserRepository;

@Service
public class AdministratorService {
	@Autowired
	private UserRepository repository;

	public List<User> fetchAllUsers() {
		try {
			return repository.showAllUsersByRole(UserRole.USER);
		} catch (Exception e) {
			return null;
		}

	}
}
