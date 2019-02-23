package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class LinhaComProblema implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "anoTermino")
	private String anoTermino;
	@Column(name = "mesTermino")
	private String mesTermino;
	@Column(name = "cnpjEstabelecimento")
	private String cnpjEstabelecimento;
	@Column(name = "empresaNome")
	private String empresaNome;
	@Column(name = "rua")
	private String rua;
	@Column(name = "cepEstabelecimento")
	private String cepEstabelecimento;
	@Column(name = "bairroEstabelecimento")
	private String bairroEstabelecimento;
	@Column(name = "municipio")
	private String municipio;
	@Column(name = "estado")
	private String estado;
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
		LinhaComProblema other = (LinhaComProblema) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAnoTermino() {
		return anoTermino;
	}
	public void setAnoTermino(String anoTermino) {
		this.anoTermino = anoTermino;
	}
	public String getMesTermino() {
		return mesTermino;
	}
	public void setMesTermino(String mesTermino) {
		this.mesTermino = mesTermino;
	}
	public String getCnpjEstabelecimento() {
		return cnpjEstabelecimento;
	}
	public void setCnpjEstabelecimento(String cnpjEstabelecimento) {
		this.cnpjEstabelecimento = cnpjEstabelecimento;
	}
	public String getEmpresaNome() {
		return empresaNome;
	}
	public void setEmpresaNome(String empresaNome) {
		this.empresaNome = empresaNome;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCepEstabelecimento() {
		return cepEstabelecimento;
	}
	public void setCepEstabelecimento(String cepEstabelecimento) {
		this.cepEstabelecimento = cepEstabelecimento;
	}
	public String getBairroEstabelecimento() {
		return bairroEstabelecimento;
	}
	public void setBairroEstabelecimento(String bairroEstabelecimento) {
		this.bairroEstabelecimento = bairroEstabelecimento;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
