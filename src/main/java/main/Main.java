package main;

import javax.persistence.EntityManager;

import domain.Cidade;
import infra.JPAUtil;



public class Main {
	
	public static void main(String[] args) {
		System.out.println("Teste 1");

		EntityManager em = JPAUtil.getEntityManager();
		
		Cidade cidade = new Cidade();
		cidade.setNome("teste");
		
		try {
			em.getTransaction().begin();
			
			em.persist(cidade);
			
			
			
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
		System.out.println("Teste 2");
		System.exit(0);
	}

}
