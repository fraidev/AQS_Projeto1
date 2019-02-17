package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_bairro")
public class Bairro  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "cnpj")
	private String nome;
	@OneToMany(mappedBy="bairro")
	private List<Fiscalizacao> fiscalizacoes;
	@OneToMany(mappedBy="bairro")
	private List<Empresa> Empresas;
	@ManyToOne
    @JoinColumn(name="cidadeId")
	private Cidade cidade;
	
	public List<Fiscalizacao> getFiscalizacoes() {
		return fiscalizacoes;
	}
	public void setFiscalizacoes(List<Fiscalizacao> fiscalizacoes) {
		this.fiscalizacoes = fiscalizacoes;
	}
	public List<Empresa> getEmpresas() {
		return Empresas;
	}
	public void setEmpresas(List<Empresa> empresas) {
		Empresas = empresas;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
		Bairro other = (Bairro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
