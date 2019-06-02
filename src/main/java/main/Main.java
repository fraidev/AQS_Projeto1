package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import domain.Bairro;
import domain.Cidade;
import domain.Empresa;
import domain.Fiscalizacao;
import domain.LinhaComProblema;
import domain.Uf;
import infra.JPAUtil;
import services.CpfCnpjUtils;



public class Main {
	
	public static void main(String[] args) {
		System.out.println("Init");

		EntityManager em = JPAUtil.getEntityManager();
		
		String csvFile = "C:/Empresas - São Paulo.csv";
    	String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] excelLine = line.split(";");

        		String anoTermino = excelLine[0];
        		String mesTermino = excelLine[1];
        		String cnpjEstabelecimento = excelLine[2];
        		String empresaNome = excelLine[3];
        		String rua = excelLine[4];
        		String cepEstabelecimento = excelLine[5];
        		String bairroEstabelecimento = excelLine[6];
        		String municipio = excelLine[7];
        		String estado = excelLine[8];
        		
        		//Valid CNPJ
        		if(CpfCnpjUtils.isValidCNPJ(cnpjEstabelecimento)) {
        			Uf uf = new Uf();
        			uf.setSigla("SP");
        			uf.setNome(estado);
					String jpqlUf = "select u from Uf u where u.sigla = :pNome"; TypedQuery<Uf>
					queryUf = em.createQuery(jpqlUf, Uf.class); queryUf.setParameter("pNome",
					uf.getSigla()); try { uf = queryUf.getSingleResult(); } catch
					(NoResultException ex) { System.out.println(ex); }			 
        			
        			Cidade cidade = new Cidade();
        			cidade.setNome(municipio);
        			cidade.setUf(uf);
					String jpqlCidade = "select u from Cidade u where u.nome = :pNome"; TypedQuery<Cidade>
					queryCidade = em.createQuery(jpqlCidade, Cidade.class); queryCidade.setParameter("pNome",
					cidade.getNome()); try { cidade = queryCidade.getSingleResult(); } catch
					(NoResultException ex) { System.out.println(ex); }		
        			
        			Bairro bairro = new Bairro();
        			bairro.setNome(bairroEstabelecimento);
        			bairro.setCidade(cidade);
					String jpqlBairro = "select u from Bairro u where u.nome = :pNome"; TypedQuery<Bairro>
					queryBairro = em.createQuery(jpqlBairro, Bairro.class); queryBairro.setParameter("pNome",
					bairro.getNome()); try { bairro = queryBairro.getSingleResult(); } catch
					(NoResultException ex) { System.out.println(ex); }	
        			
        			Empresa empresa = new Empresa();
        			empresa.setCnpj(cnpjEstabelecimento);
        			empresa.setRazaoSocial(empresaNome);
        			empresa.setLogadouro(rua);
        			empresa.setCep(cepEstabelecimento);
        			empresa.setBairro(bairro);
        			empresa.setCidade(cidade);
        			empresa.setUf(uf);
					String jpqlEmpresa = "select u from Empresa u where u.razaoSocial = :pNome"; TypedQuery<Empresa>
					queryEmpresa = em.createQuery(jpqlEmpresa, Empresa.class); queryEmpresa.setParameter("pNome",
					empresa.getRazaoSocial()); try { empresa = queryEmpresa.getSingleResult(); } catch
					(NoResultException ex) { System.out.println(ex); }	
        			
        			Fiscalizacao fiscalizacao = new Fiscalizacao();
        			
        			String[] dateExcel = mesTermino.split("/");
        			int ano = Integer.parseInt(dateExcel[0]);
        			int mes = Integer.parseInt(dateExcel[1]);
        					
        			// alguma data no primeiro dia
        			LocalDate data = LocalDate.of(ano, mes, 1);
        			// ajustar para a última sexta-feira do mês
        			LocalDate ultimaSexta = data.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
        			
        			fiscalizacao.setData(ultimaSexta);
        			fiscalizacao.setEmpresa(empresa);

//					String jpqlFiscalizacao = "select u from Fiscalizacao u where u.empresa = :pEmpresa and u.data = :pData"; TypedQuery<Fiscalizacao>
//					queryFiscalizacao = em.createQuery(jpqlFiscalizacao, Fiscalizacao.class); 
//					queryFiscalizacao.setParameter("pEmpresa", fiscalizacao.getEmpresa()); 
//					queryFiscalizacao.setParameter("pData", fiscalizacao.getData()); 
//					try { fiscalizacao = queryFiscalizacao.getSingleResult(); } 
//					catch(NoResultException ex) 
//					{ 
//						System.out.println(ex); 
//					}	
					
            		
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
                		try {
            			em.getTransaction().begin();
            			LinhaComProblema linhaComProblema = new LinhaComProblema();
            			linhaComProblema.setAnoTermino(anoTermino);
            			linhaComProblema.setMesTermino(mesTermino);
            			linhaComProblema.setCnpjEstabelecimento(cnpjEstabelecimento);
            			linhaComProblema.setEmpresaNome(empresaNome);
            			linhaComProblema.setRua(rua);
            			linhaComProblema.setCepEstabelecimento(cepEstabelecimento);
            			linhaComProblema.setBairroEstabelecimento(bairroEstabelecimento);
            			linhaComProblema.setMunicipio(municipio);
            			linhaComProblema.setEstado(estado);
            			em.persist(linhaComProblema);
            			em.getTransaction().commit();
                		}catch (Exception ex) {
                			em.getTransaction().rollback();
                		}
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