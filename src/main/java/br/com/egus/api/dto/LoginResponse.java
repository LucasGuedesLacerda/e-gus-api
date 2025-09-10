package br.com.egus.api.dto;

public class LoginResponse {
    private Long id;
    private String nome;
    private String tipo;
    private String cargo;
    private Integer idMercado;


    public LoginResponse(Long id, String nome, String tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    public LoginResponse(Long id, String nome, String tipo, String cargo, int idMercado) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.cargo = cargo;
        this.idMercado = idMercado;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public Integer getIdMercado() { return idMercado; }
    public void setIdMercado(Integer idMercado) { this.idMercado = idMercado; }
}
