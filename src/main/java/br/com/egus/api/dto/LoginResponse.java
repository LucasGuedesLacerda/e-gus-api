package br.com.egus.api.dto;

public class LoginResponse {
    private Long id;
    private String cargo;
    private String nome;
    private int idMercado;

    public LoginResponse(Long id, String cargo, String nome, int idMercado) {
        this.id = id;
        this.cargo = cargo;
        this.nome = nome;
        this.idMercado = idMercado;
    }
    public int getIdMercado() {
        return idMercado;
    }
    public void setIdMercado(int idMercado) {
        this.idMercado = idMercado;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}