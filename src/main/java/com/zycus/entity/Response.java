package com.zycus.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_responses")
public class Response {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int responseNumber;

	private String response;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "questionId")
	private Question question;

	@ManyToOne
	@JoinColumn(name = "surveyId")
	private Survey survey;

	public int getResponseNumber() {
		return responseNumber;
	}

	public void setResponseNumber(int responseNumber) {
		this.responseNumber = responseNumber;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
}
