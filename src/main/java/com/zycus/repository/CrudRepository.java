package com.zycus.repository;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/*import org.omg.PortableServer.THREAD_POLICY_ID;*/
import org.springframework.stereotype.Component;

import com.zycus.customExceptions.CouldNotPerformOperationException;
import com.zycus.customExceptions.EntityNotFoundInDatabaseException;
import com.zycus.customExceptions.NoRecordsFoundException;

@Component
@Transactional
public class CrudRepository<T> /* implements Thread.UncaughtExceptionHandler */ {

	@PersistenceContext
	private EntityManager entityManager;

	public void addRecord(T myObject) throws CouldNotPerformOperationException {
		try {
			entityManager.persist(myObject);
		} catch (IllegalArgumentException | EntityExistsException e) {
			throw new CouldNotPerformOperationException("Unable to save this record : ", myObject, myObject.getClass());
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> fetchAll(Class<T> clazz) throws NoRecordsFoundException {
		String jpql = "select o from " + clazz.getName() + " as o";
		try {
			return (List<T>) entityManager.createQuery(jpql).getResultList();
		} catch (Exception e) {
			throw new NoRecordsFoundException("No records exist", clazz);
		}
	}

	public T fetchById(Class<T> clazz, Object id) throws EntityNotFoundInDatabaseException {
		try {

			return entityManager.find(clazz, id);
		} catch (Exception e) {
			throw new EntityNotFoundInDatabaseException("Record does not exist for the given ID.");
		}

	}

	public void update(T myGenericObject) {
		entityManager.merge(myGenericObject);
	}

	public void delete(Class<T> className, Object id) throws CouldNotPerformOperationException {
		try {
			Object object = entityManager.find(className, id);
			entityManager.remove(object);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * @Override public void uncaughtException(Thread t, Throwable e) {
	 * e.setStackTrace("");
	 * 
	 * }
	 */
}
