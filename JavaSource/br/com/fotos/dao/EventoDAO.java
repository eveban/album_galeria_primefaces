package br.com.fotos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fotos.modelo.Evento;

public class EventoDAO {

	private EntityManager em;

	public List<Evento> listaOrdenada() {
		em = new JPAUtil().getEntityManager();
		String jpql = "select e from Evento e order by e.data desc";
		Query query = this.em.createQuery(jpql);
		//em.close();
		return query.getResultList();

	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
