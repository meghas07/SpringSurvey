package com.zycus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.zycus.entity.User;
import com.zycus.myenums.UserRole;
import com.zycus.customExceptions.EntityNotFoundInDatabaseException;
import com.zycus.customExceptions.NoRecordsFoundException;

@Component
@Transactional
public class UserRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public User checkUserLoginCredentials(int userId) {
		return entityManager.find(User.class, userId);
	}

	public List<User> showAllUsersByRole(UserRole role) {

		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = criteria.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.where(criteria.equal(root.<String>get("role"), role));
		query.select(root);
		TypedQuery<User> myQuery = entityManager.createQuery(query);

		List<User> listOfUsers;
		try {

			listOfUsers = myQuery.getResultList();

		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}

		return listOfUsers;

	}

}
