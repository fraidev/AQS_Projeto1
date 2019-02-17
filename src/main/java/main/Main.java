package main;

import javax.persistence.EntityManager;
import domain.Bairro;
import domain.Cidade;
import domain.Empresa;
import domain.Fiscalizacao;
import domain.Uf;
import infra.JPAUtil;



public class Main {
	
	public static void main(String[] args) {
		System.out.println("Teste 1");

		EntityManager em = JPAUtil.getEntityManager();
		
		String mesTermino = "2011/08";
		String cnpjEstabelecimento = "Sem Informação";
		String empresaNome = "CONFECÇÃO A";
		String rua = "RUA IMIGRANTES, 525";
		String cepEstabelecimento = "SI";
		String bairroEstabelecimento = "JARDIM IPIRANGA";
		String municipio = "Americana";
		String estado = "São Paulo";
		
		Uf uf = new Uf();
		uf.setSigla(estado);
		uf.setNome("SP");
		
		Cidade cidade = new Cidade();
		cidade.setNome(municipio);
		cidade.setUf(uf);
		
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
		} finally {
			em.close();
		}
		
		System.out.println("Teste 2");
		System.exit(0);
	}
}