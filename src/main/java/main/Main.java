package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import domain.Bairro;
import domain.Cidade;
import domain.Empresa;
import domain.Fiscalizacao;
import domain.Uf;
import infra.JPAUtil;



public class Main {
	
	public static void main(String[] args) {
		System.out.println("Init");

		EntityManager em = JPAUtil.getEntityManager();
		
		String csvFile = "C:/Empresas - São Paulo.csv";
    	String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] excelLine = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                
        		String mesTermino = excelLine[1];
        		String cnpjEstabelecimento = excelLine[2];
        		String empresaNome = excelLine[3];
        		String rua = excelLine[4];
        		String cepEstabelecimento = excelLine[5];
        		String bairroEstabelecimento = excelLine[6];
        		String municipio = excelLine[7];
        		String estado = excelLine[8];
        		
        		//cep valid
                String regex = "\\d{5}-\\d{3}";
        		if(cepEstabelecimento.matches(regex)) {
        			Uf uf = new Uf();
        			uf.setSigla("SP");
        			uf.setNome(estado);

    				String jpqlUf = "select u from Uf u where u.sigla = :pNome";
    				TypedQuery<Uf> queryUf = em.createQuery(jpqlUf, Uf.class);
    				queryUf.setParameter("pNome", uf.getSigla());
    				try {
    					uf = queryUf.getSingleResult();
    				} catch (NoResultException ex) {
    					System.out.println(ex);
    				}
        			
        			Cidade cidade = new Cidade();
        			cidade.setNome(municipio);
        			cidade.setUf(uf);

//    				String jpql = " select u from Cidade u where u.nome = :pNome";
//    				TypedQuery<Cidade> query = em.createQuery(jpql, Cidade.class);
//    				query.setParameter("pNome", cidade.getNome().trim().toLowerCase());
//    				try {
//    					cidade = query.getSingleResult();
//    				} catch (NoResultException ex) {
//    				}
        			
        			Bairro bairro = new Bairro();
        			bairro.setNome(bairroEstabelecimento);
        			bairro.setCidade(cidade);
        			
        			Empresa empresa = new Empresa();
        			empresa.setCnpj(cnpjEstabelecimento);
        			empresa.setRazaoSocial(empresaNome);
        			empresa.setLogadouro(rua);
        			empresa.setCep(cepEstabelecimento);
        			empresa.setBairro(bairro);
        			empresa.setCidade(cidade);
        			empresa.setUf(uf);
        			
        			Fiscalizacao fiscalizacao = new Fiscalizacao();
        			fiscalizacao.setData(mesTermino);
        			fiscalizacao.setEmpresa(empresa);
            		
            		try {
            			em.getTransaction().begin();
            			em.persist(uf);
            			em.persist(cidade);
            			em.persist(bairro);
            			em.persist(empresa);
            			em.persist(fiscalizacao);
            			em.getTransaction().commit();
            			
            		} catch (Exception e) {
            			em.getTransaction().rollback();
            		} 
        		}
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

		em.close();
		System.out.println("Finish");
		System.exit(0);
	}
}