package br.com.egus.api.dto;

import java.util.Map;

public class LoginResponse {
    private Long id;
    private String nome;
    private String tipo;
    private String cargo;
    private Integer idMercado;
    private Long cpf;
    private String email;
      private Map<String, Object> preferences;



    public LoginResponse(Long id, String nome, String tipo, Long cpf, String email, Map<String, Object> preferences) { // Para usuário
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.cpf = cpf;
        this.email = email;
        this.preferences = preferences;
    }

    public LoginResponse(Long id, String nome, String tipo, String cargo, int idMercado) { // Para funcionário
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

    public Long getCpf() { return cpf; }
    public void setCpf(Long cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
      public Map<String, Object> getPreferences() {
        return preferences;
    }
    public void setPreferences(Map<String, Object> preferences) {
        this.preferences = preferences;
    }


 
}
