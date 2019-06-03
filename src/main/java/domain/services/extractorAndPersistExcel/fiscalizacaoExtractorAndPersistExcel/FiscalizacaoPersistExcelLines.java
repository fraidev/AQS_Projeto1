package domain.services.extractorAndPersistExcel.fiscalizacaoExtractorAndPersistExcel;

import domain.models.*;
import domain.services.extractorAndPersistExcel.PersistExcelLine;
import domain.valueObject.ExcelFiscalizacao;
import domain.services.validators.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class FiscalizacaoPersistExcelLines implements PersistExcelLine {
    String[] excelLine;
    EntityManager em;
    ExcelFiscalizacao excelFiscalizacao;
    Uf uf;
    Cidade cidade;
    Bairro bairro;
    Empresa empresa;
    Fiscalizacao fiscalizacao;

    @Override
    public void PersistLines(String[] excelLine, EntityManager em) throws Exception {
        this.excelLine = excelLine;
        this.em = em;

        this.excelFiscalizacao = new ExcelFiscalizacao(this.excelLine);

        if(!isValidLines()){
            return;
        }

        this.uf = createUf();
        this.cidade = createCidade();
        this.bairro = createBairro();
        this.empresa = createEmpresa();
        this.fiscalizacao = createFiscalizacao();

        try {
            this.em.getTransaction().begin();
            this.em.persist(uf);
            this.em.persist(cidade);
            this.em.persist(bairro);
            this.em.persist(empresa);
            this.em.persist(fiscalizacao);
            this.em.getTransaction().commit();

        } catch (Exception e) {
            this.em.getTransaction().rollback();
            try {
                persistLinhaComProblema();
            }catch (Exception ex) {
                this.em.getTransaction().rollback();
            }
        }
    }

    private void persistLinhaComProblema(){
        this.em.getTransaction().begin();
        LinhaComProblema linhaComProblema = new LinhaComProblema(this.excelLine);
        this.em.persist(linhaComProblema);
        this.em.getTransaction().commit();
    }

    private boolean isValidLines(){
        if(!CpfCnpjUtils.isValidCNPJ(excelFiscalizacao.getCnpjEstabelecimento())) {
            persistLinhaComProblema();
            return false;
        }
        return true;
    }

    private Uf createUf() throws Exception {
        Uf uf = new Uf();
        uf.setNome(excelFiscalizacao.getEstado());
        UfUtils ufUtils = new UfUtils();
        uf.setSigla(ufUtils.getSigla(excelFiscalizacao.getEstado()));

        String jpqlUf = "select u from Uf u where u.sigla = :pNome"; TypedQuery<Uf>
                queryUf = this.em.createQuery(jpqlUf, Uf.class); queryUf.setParameter("pNome",
                uf.getSigla()); try { uf = queryUf.getSingleResult(); } catch
        (NoResultException ex) { System.out.println(this.em); }

        return uf;
    }

    private Cidade createCidade(){
        Cidade cidade = new Cidade();
        cidade.setNome(excelFiscalizacao.getMunicipio());
        cidade.setUf(uf);

        String jpqlCidade = "select u from Cidade u where u.nome = :pNome"; TypedQuery<Cidade>
                queryCidade = this.em.createQuery(jpqlCidade, Cidade.class); queryCidade.setParameter("pNome",
                cidade.getNome()); try { cidade = queryCidade.getSingleResult(); } catch
        (NoResultException ex) { System.out.println(this.em); }
        return cidade;
    }

    private Bairro createBairro(){
        Bairro bairro = new Bairro();
        bairro.setNome(this.excelFiscalizacao.getBairroEstabelecimento());
        bairro.setCidade(this.cidade);
        bairro.setUf(this.uf);

        String jpqlBairro = "select u from Bairro u where u.nome = :pNome"; TypedQuery<Bairro>
                queryBairro = this.em.createQuery(jpqlBairro, Bairro.class); queryBairro.setParameter("pNome",
                bairro.getNome()); try { bairro = queryBairro.getSingleResult(); } catch
        (NoResultException ex) { System.out.println(this.em); }
        return bairro;
    }

    private Empresa createEmpresa(){
        Empresa empresa = new Empresa();
        empresa.setCnpj(excelFiscalizacao.getCnpjEstabelecimento());
        empresa.setRazaoSocial(excelFiscalizacao.getEmpresaNome());
        empresa.setLogadouro(excelFiscalizacao.getRua());
        empresa.setCep(excelFiscalizacao.getCepEstabelecimento());
        empresa.setBairro(bairro);
        empresa.setCidade(cidade);
        empresa.setUf(uf);

        String jpqlEmpresa = "select u from Empresa u where u.razaoSocial = :pNome"; TypedQuery<Empresa>
                queryEmpresa = this.em.createQuery(jpqlEmpresa, Empresa.class); queryEmpresa.setParameter("pNome",
                empresa.getRazaoSocial()); try { empresa = queryEmpresa.getSingleResult(); } catch
        (NoResultException ex) { System.out.println(ex); }
        return empresa;
    }

    private Fiscalizacao createFiscalizacao(){
        Fiscalizacao fiscalizacao = new Fiscalizacao();

        String[] dateExcel = excelFiscalizacao.getMesTermino().split("/");
        int ano = Integer.parseInt(dateExcel[0]);
        int mes = Integer.parseInt(dateExcel[1]);

        // alguma data no primeiro dia
        LocalDate data = LocalDate.of(ano, mes, 1);

        // ajustar para a última sexta-feira do mês
        LocalDate ultimaSexta = data.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));

        fiscalizacao.setData(ultimaSexta);
        fiscalizacao.setEmpresa(empresa);

        return fiscalizacao;
    }

}
