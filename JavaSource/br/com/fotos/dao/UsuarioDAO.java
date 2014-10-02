package br.com.fotos.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fotos.modelo.Usuario;

public class UsuarioDAO {

	public boolean existe(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		String jpql = "select u from Usuario u where u.login = :pLogin and u.senha = :pSenha";
		Query query = em.createQuery(jpql);
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		boolean encontrado = !query.getResultList().isEmpty();
		em.getTransaction().commit();
		em.close();

		return encontrado;

	}

}
