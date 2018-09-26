package com.zycus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.zycus.entity.Response;
import com.zycus.entity.SharedSurveysWithUser;
import com.zycus.entity.Survey;
import com.zycus.entity.User;
import com.zycus.myenums.ResponseStatus;

@Component
@Transactional
public class ResponseRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Survey> showAllSurveyByAdmin(Object adminId) {

		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<Survey> query = criteria.createQuery(Survey.class);
		Root<Survey> root = query.from(Survey.class);
		query.where(criteria.equal(root.<User>get("user").<Integer>get("id"), adminId));
		query.select(root);
		TypedQuery<Survey> myQuery = entityManager.createQuery(query);

		List<Survey> listOfSurveys;
		try {

			listOfSurveys = myQuery.getResultList();

		} catch (NoResultException e) {
			e.printStackTrace();
			System.out.println("in catch");
			return null;
		}

		return listOfSurveys;

	}

	public SharedSurveysWithUser returnAsnweredSurvey(SharedSurveysWithUser sharedSurveysWithUser) {
		SharedSurveysWithUser answeredSurvey;
		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<SharedSurveysWithUser> query = criteria.createQuery(SharedSurveysWithUser.class);
		Root<SharedSurveysWithUser> root = query.from(SharedSurveysWithUser.class);
		query.where(criteria.and(
				criteria.equal(root.<User>get("user").<Integer>get("id"), sharedSurveysWithUser.getUser().getId()),
				criteria.equal(root.<Survey>get("survey").<Integer>get("surveyId"),
						sharedSurveysWithUser.getSurvey().getSurveyId())));
		query.select(root);
		TypedQuery<SharedSurveysWithUser> myQuery = entityManager.createQuery(query);

		try {

			answeredSurvey = myQuery.getSingleResult();

		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}

		return answeredSurvey;
	}

	public List<Response> listOfResponses(int surveyId, int userId) {
		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<Response> query = criteria.createQuery(Response.class);
		Root<Response> root = query.from(Response.class);
		query.where(criteria.and(criteria.equal(root.<User>get("user").<Integer>get("id"), userId),
				criteria.equal(root.<Survey>get("survey").<Integer>get("surveyId"), surveyId)));
		query.select(root);
		TypedQuery<Response> myQuery = entityManager.createQuery(query);
		try {
			return myQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void deleteResponsesForSurvey(int surveyId) {
		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaDelete<Response> query = criteria.createCriteriaDelete(Response.class);
		Root<Response> root = query.from(Response.class);
		query.where(criteria.equal(root.<Survey>get("survey").<Integer>get("surveyId"), surveyId));
		Query myQuery = entityManager.createQuery(query);
		myQuery.executeUpdate();
	}

}
