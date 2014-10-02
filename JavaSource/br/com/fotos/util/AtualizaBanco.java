package br.com.fotos.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AtualizaBanco {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("eventos");
	}

}
