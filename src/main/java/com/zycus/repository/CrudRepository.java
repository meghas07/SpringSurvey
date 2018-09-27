package com.zycus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.zycus.customExceptions.EntityNotFoundInDatabaseException;
import com.zycus.customExceptions.NoRecordsFoundException;

@Component
@Transactional
public class CrudRepository<T> {

	@PersistenceContext
	private EntityManager entityManager;

	public void addRecord(T myObject) {
		entityManager.persist(myObject);
	}

	@SuppressWarnings("unchecked")
	public List<T> fetchAll(Class<T> clazz) throws NoRecordsFoundException {
		String jpql = "select o from " + clazz.getName() + " as o";
		try {
			return (List<T>) entityManager.createQuery(jpql).getResultList();
		} catch (Exception e) {
			throw new NoRecordsFoundException("Record does not exist for the given details .");
		}
	}

	public T fetchById(Class<T> clazz, Object id) throws EntityNotFoundInDatabaseException {
		try {
			return entityManager.find(clazz, id);
		} catch (Exception e) {
			throw new EntityNotFoundInDatabaseException("Record does not exist for the given details.");
		}

	}

	public void update(T myGenericObject) {
		entityManager.merge(myGenericObject);
	}

	public void delete(Class<T> className, Object id) {
		Object object = entityManager.find(className, id);
		entityManager.remove(object);
	}
}
