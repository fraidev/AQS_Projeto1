package domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_fiscalizacao")
public class Fiscalizacao implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "data")
	private LocalDate data;
	@Column(name = "razaoSocial")
	private String razaoSocial;
	@Column(name = "logadouro")
	private String logadouro;
	@Column(name = "cep")
	private String cep;
	@ManyToOne
    @JoinColumn(name="ufId")
	private Uf uf;
	@ManyToOne
    @JoinColumn(name="bairroId")
	private Bairro bairro;
	@ManyToOne
    @JoinColumn(name="CidadeId")
	private Cidade cidade;
	@ManyToOne
    @JoinColumn(name="EmpresaId")
	private Empresa empresa;
	
	public Uf getUf() {
		return uf;
	}
	public Bairro getBairro() {
		return bairro;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
		this.razaoSocial = empresa.getRazaoSocial();
		this.logadouro = empresa.getLogadouro();
		this.cep = empresa.getCep();
		this.bairro = empresa.getBairro();
		this.cidade = empresa.getCidade();
		this.uf = empresa.getUf();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public String getLogadouro() {
		return logadouro;
	}
	public void setLogadouro(String logadouro) {
		this.logadouro = logadouro;
	}
	public String getCep() {
		return cep;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fiscalizacao other = (Fiscalizacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
