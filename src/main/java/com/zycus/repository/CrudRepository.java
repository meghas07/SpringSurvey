package com.zycus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.zycus.customExceptions.EntityNotFoundInDatabaseException;
import com.zycus.entity.Survey;
import com.zycus.entity.User;

@Component
@Transactional
public class CrudRepository<T> {

	@PersistenceContext
	private EntityManager entityManager;

	public void addRecord(T myObject) {
		entityManager.persist(myObject);
	}

	public List<T> fetchAll(Class<T> clazz) {
		String jpql = "select o from " + clazz.getName() + " as o";
		return (List<T>) entityManager.createQuery(jpql).getResultList();
	}

	public T fetchById(Class<T> clazz, Object id) {
		T genericObject = entityManager.find(clazz, id);
		if (genericObject == null)
			return null;
		else
			return genericObject;
	}

	public void update(T myGenericObject) {
		entityManager.merge(myGenericObject);
	}

	public void delete(Class className, Object id) {
		@SuppressWarnings("unchecked")
		Object object = entityManager.find(className, id);
		entityManager.remove(object);
	}
}
