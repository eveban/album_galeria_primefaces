package br.com.fotos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fotos.modelo.Evento;
import br.com.fotos.modelo.Foto;

public class FotoDAO {
	private EntityManager em;
	
	public List<Foto> buscaPorEvento(Evento eventoSelecionado) {
		em = new JPAUtil().getEntityManager();
		String jpql = "select m from Foto m where m.evento = :pEvento";
		Query query = this.em.createQuery(jpql);
		query.setParameter("pEvento", eventoSelecionado);
		System.out.println("Este é o evento selecionado: " + eventoSelecionado);
		return query.getResultList();
	}

	public List<Foto> listaTodasFotos(Evento eventoSelecionado) {
		em = new JPAUtil().getEntityManager();
		String jpql = "select m from Foto m where m.evento = :pEvento";
		System.out.println("Este é o evento selecionado: " + eventoSelecionado);
		Query query = this.em.createQuery(jpql);
		query.setParameter("pEvento", eventoSelecionado);
		return query.getResultList();

	}


	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
