package com.zycus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.zycus.entity.Question;
import com.zycus.entity.Survey;

@Component
public class QuestionRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Question> showAllQuestionsInSurvey(Object surveyId) {

		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<Question> query = criteria.createQuery(Question.class);
		Root<Question> root = query.from(Question.class);
		query.where(criteria.equal(root.<Survey>get("survey").<Integer>get("surveyId"), surveyId));
		query.select(root);
		TypedQuery<Question> myQuery = entityManager.createQuery(query);

		List<Question> listOfQuestions;
		try {

			listOfQuestions = myQuery.getResultList();

		} catch (NoResultException e) {
			e.printStackTrace();
			System.out.println("in catch");
			return null;
		}

		return listOfQuestions;

	}

}
