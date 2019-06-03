package domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tb_uf")
public class Uf  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	@Column(name = "sigla", unique = true)
	private String sigla;
	@Column(name = "nome", unique = true)
	private String nome;
	@OneToMany(mappedBy="uf", cascade = CascadeType.ALL)
	private List<Bairro> bairros;
	@OneToMany(mappedBy="uf", cascade = CascadeType.ALL)
	private List<Cidade> cidades;
	@OneToMany(mappedBy="uf")
	private List<Empresa> empresas;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
		Uf other = (Uf) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Uf:[%d, %s]", id, nome);
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

//	public List<Fiscalizacao> getFiscalizacoes() {
//		return fiscalizacoes;
//	}
//
//	public void setFiscalizacoes(List<Fiscalizacao> fiscalizacoes) {
//		this.fiscalizacoes = fiscalizacoes;
//	}

	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}
}
