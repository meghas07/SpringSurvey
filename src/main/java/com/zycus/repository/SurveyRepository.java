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

import com.zycus.customExceptions.NoRecordsFoundException;
import com.zycus.entity.SharedSurveysWithUser;
import com.zycus.entity.Survey;
import com.zycus.entity.User;

@Component
public class SurveyRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public List<Survey> showAllSurveyByAdmin(Object adminId) {

		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<Survey> query = criteria.createQuery(Survey.class);
		Root<Survey> root = query.from(Survey.class);
		query.where(criteria.equal(root.<User>get("user").<Integer>get("id"), adminId));
		query.select(root);
		TypedQuery<Survey> myQuery = entityManager.createQuery(query);

		try {

			return myQuery.getResultList();

		} catch (NoResultException e) {
			e.printStackTrace();
			throw new NoRecordsFoundException("No Surveys created by ", adminId);
		}

	}

	public int checkIfSurveyAlreadyShared(SharedSurveysWithUser sharedSurveyWithUser) {
		try {
			List<SharedSurveysWithUser> listOfUsers = showAllUsersByIdFromSharedSurveys(sharedSurveyWithUser.getUser());
			for (SharedSurveysWithUser user : listOfUsers) {
				if (user.getSurvey().getSurveyId() == sharedSurveyWithUser.getSurvey().getSurveyId())
					return user.getUser().getId();

			}
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	public List<SharedSurveysWithUser> showAllUsersByIdFromSharedSurveys(User user) {
		String jpql = "select obj from SharedSurveysWithUser as obj where obj.user.id=" + user.getId();

		List<SharedSurveysWithUser> listOfUsers;
		try {

			listOfUsers = entityManager.createQuery(jpql).getResultList();

		} catch (NoResultException e) {
			e.printStackTrace();

			return null;
		}

		return listOfUsers;

	}

	@Transactional
	public void deleteSurveyShared(int surveyId) {

		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaDelete<SharedSurveysWithUser> query = criteria.createCriteriaDelete(SharedSurveysWithUser.class);
		Root<SharedSurveysWithUser> root = query.from(SharedSurveysWithUser.class);
		query.where(criteria.equal(root.<Survey>get("survey").<Integer>get("surveyId"), surveyId));
		Query myQuery = entityManager.createQuery(query);

		myQuery.executeUpdate();

	}

	public List<SharedSurveysWithUser> showAllSurveyForUser(Object userId) {
		System.out.println(userId + "repsoitory");

		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<SharedSurveysWithUser> query = criteria.createQuery(SharedSurveysWithUser.class);
		Root<SharedSurveysWithUser> root = query.from(SharedSurveysWithUser.class);
		query.where(criteria.equal(root.<User>get("user").<Integer>get("id"), userId));
		query.select(root);
		TypedQuery<SharedSurveysWithUser> myQuery = entityManager.createQuery(query);

		List<SharedSurveysWithUser> listOfSharedSurveys;

		try {

			listOfSharedSurveys = myQuery.getResultList();

		} catch (NoResultException e) {
			e.printStackTrace();
			System.out.println("in catch");
			return null;
		}

		return listOfSharedSurveys;

	}

}
