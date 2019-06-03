package domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tb_empresa")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "cnpj")
	private String cnpj;
	@Column(name = "razaoSocial")
	private String razaoSocial;
	@Column(name = "logadouro")
	private String logadouro;
	@Column(name = "cep")
	private String cep;
	@ManyToOne()
    @JoinColumn(name="bairroId")
	private Bairro bairro;
	@ManyToOne()
	@JoinColumn(name="cidadeId")
	private Cidade cidade;
	@ManyToOne()
	@JoinColumn(name="ufId")
	private Uf uf;
	@OneToMany(mappedBy="empresa")
	private List<Fiscalizacao> fiscalizacoes;
	
	public Uf getUf() {
		return uf;
	}
	public void setUf(Uf uf) {
		this.uf = uf;
	}
	public Bairro getBairro() {
		return bairro;
	}
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public List<Fiscalizacao> getFiscalizacoes() {
		return fiscalizacoes;
	}
	public void setFiscalizacoes(List<Fiscalizacao> fiscalizacoes) {
		this.fiscalizacoes = fiscalizacoes;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
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
	public void setCep(String cep) {
		this.cep = cep;
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
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}