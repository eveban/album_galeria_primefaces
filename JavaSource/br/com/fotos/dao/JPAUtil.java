package br.com.fotos.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("eventos");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
