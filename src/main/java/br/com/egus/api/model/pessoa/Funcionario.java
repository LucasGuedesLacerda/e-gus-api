package br.com.egus.api.model.pessoa;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario", schema = "mkt_auto")
public class Funcionario extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ⚠️ importante que seja Long
    private Long id;

    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @Column(name = "id_mercado")
    private Integer idMercado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Integer getIdMercado() {
        return idMercado;
    }

    public void setIdMercado(Integer idMercado) {
        this.idMercado = idMercado;
    }
}


