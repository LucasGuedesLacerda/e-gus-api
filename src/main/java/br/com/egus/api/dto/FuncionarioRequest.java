package br.com.egus.api.dto;

public class FuncionarioRequest {
    private String nome;
    private String email;
    private String senha;
    private Boolean ativo;
    private String cargo; // string do enum
    private Integer idMercado;

    // Getters e setters
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getIdMercado() { return idMercado; }
    public void setIdMercado(Integer idMercado) { this.idMercado = idMercado; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
