package com.zycus.dto;

import com.zycus.entity.Question;
import com.zycus.entity.Response;

public class QuestionAnswer {

	private Question question;
	private Response response;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

}
