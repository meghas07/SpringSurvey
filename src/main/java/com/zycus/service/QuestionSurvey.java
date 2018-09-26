package com.zycus.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.zycus.repository.CrudRepository;

public class QuestionSurvey {

	@Autowired
	private CrudRepository repository;

	public <T> void addQuestion(T myObject) {
		repository.addRecord(myObject);
	}

}
