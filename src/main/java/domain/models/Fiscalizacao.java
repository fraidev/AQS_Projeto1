package domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	@ManyToOne
	@JoinColumn(name="fiscal1Id")
	private Fiscal fiscal1;

	@ManyToOne
	@JoinColumn(name="fiscal2Id")
	private Fiscal fiscal2;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="tb_fiscalizacao_has_ocorrencias", joinColumns=
			{@JoinColumn(name="fiscalizacao_id")}, inverseJoinColumns=
			{@JoinColumn(name="ocorrencia_id")})
	private List<Ocorrencia> ocorrencias = new ArrayList<>();

	@ManyToOne(optional = false)
	@JoinColumn(name="EmpresaId")
	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public Fiscal getFiscal1() {
		return fiscal1;
	}

	public void setFiscal1(Fiscal fiscal1) {
		this.fiscal1 = fiscal1;
	}

	public Fiscal getFiscal2() {
		return fiscal2;
	}

	public void setFiscal2(Fiscal fiscal2) {
		this.fiscal2 = fiscal2;
	}

	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}
}
