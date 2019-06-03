package domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="tb_ocorrencia")
public class Ocorrencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false )
    private UUID id;
    @Column(name = "codigo", unique=true)
    private String codigo;
    @Column(name = "nome")
    private String nome;
    @ManyToOne(fetch = FetchType.EAGER, optional=false)
    @JoinColumn(name="fiscalizacaoId")
    private Fiscalizacao fiscalizacao;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Fiscalizacao getFiscalizacao() {
        return fiscalizacao;
    }
    public void setFiscalizacao(Fiscalizacao fiscalizacao) {
        this.fiscalizacao = fiscalizacao;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        Ocorrencia other = (Ocorrencia) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
