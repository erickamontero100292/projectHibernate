package com.blog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.blog.model.User;

@Repository
@Transactional
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Almacena el usuario en la base de datos
	 */
	public void create(User user) {
		entityManager.persist(user);
		return;
	}

	/**
	 * Elimina el usuario de la base de datos.
	 */
	public void delete(User user) {
		if (entityManager.contains(user))
			entityManager.remove(user);
		else
			entityManager.remove(entityManager.merge(user));
		return;
	}

	/**
	 * Devuelve todos los usuarios de la base de datos.
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return entityManager.createQuery("select u from User u").getResultList();
	}

	/**
	 * Devuelve un usuario en base a su Id
	 */
	public User getById(int id) {
		return entityManager.find(User.class, id);
	}

	/**
	 * Actualiza el usuario proporcionado
	 */
	/*
	 * public void update(User user) { entityManager.merge(user); return; }
	 */

	public User update(User user) {
		return entityManager.merge(user);
	}

	
	/* OTRAS CONSULTAS */
	//Consultas utilizadas en el login
	
	public User getByEmailAndPassword(String email, String password) {
		User result;

		try {
			result = (User) entityManager
					.createNativeQuery("SELECT * FROM USER WHERE EMAIL = :email AND PASSWORD = MD5(:password)",
							User.class)
					.setParameter("email", email).setParameter("password", password).getSingleResult();

		} catch (NoResultException ex) {
			result = null;
		}

		return result;
	}
	
	//Consulta para saber el número post que ha escrito el usuario
	public int getNumPostByUser(User user) {
		int result;
		
		result = (int) entityManager.createQuery("select count(p) from Post p WHERE p.autor = :autor")
				.setParameter("autor", user)
				.setFirstResult(0)
				.setMaxResults(1)
				.getSingleResult();
		
		return result;
	}

}
