package com.zycus.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zycus.myenums.Status;

@Entity
@Table(name = "tbl_surveys")
public class Survey {

	@Id
	private int surveyId;

	private String surveyName;

	@OneToMany(mappedBy = "survey", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Question> setOfQuestionsInSurvey;

	@ManyToOne
	@JoinColumn(name = "administratorId")
	private User user;

	@Enumerated(EnumType.STRING)
	private Status currentSurveyStatus = Status.INACTIVE;

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public Set<Question> getSetOfQuestionsInSurvey() {
		return setOfQuestionsInSurvey;
	}

	public void setSetOfQuestionsInSurvey(Set<Question> setOfQuestionsInSurvey) {
		this.setOfQuestionsInSurvey = setOfQuestionsInSurvey;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return currentSurveyStatus;
	}

	public void setStatus(Status currentSurveyStatus) {
		this.currentSurveyStatus = currentSurveyStatus;
	}

}
