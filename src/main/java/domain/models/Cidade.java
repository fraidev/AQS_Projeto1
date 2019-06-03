package domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tb_cidade")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name="nome", length=50, nullable=false )
	private String nome;
	@OneToMany(mappedBy="cidade", cascade = CascadeType.ALL)
	private List<Bairro> bairros;
	@OneToMany(mappedBy="cidade")
	private List<Empresa> empresas;
	@ManyToOne
    @JoinColumn(name="ufId")
	private Uf uf;
	
	public List<Bairro> getBairros() {
		return bairros;
	}
	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	public Uf getUf() {
		return uf;
	}
	public void setUf(Uf uf) {
		this.uf = uf;
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
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
