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
import com.zycus.customExceptions.EntityNotFoundInDatabaseException;

@Component
@Transactional
public class UserRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public User checkUserLoginCredentials(User user) throws EntityNotFoundInDatabaseException {

		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = criteria.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.where(criteria.and(criteria.equal(root.<Integer>get("id"), user.getId()),
				criteria.equal(root.<String>get("password"), user.getPassword())));
		query.select(root);
		TypedQuery<User> myQuery = entityManager.createQuery(query);

		try {
			return myQuery.getSingleResult();

		} catch (NoResultException e) {
			System.out.println("catch block");
			throw new EntityNotFoundInDatabaseException("Invalid Username or Password.");
		}

	}

	public List<User> showAllUsersByRole(Object role) {

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
