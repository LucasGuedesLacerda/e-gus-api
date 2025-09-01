package br.com.egus.api.dto;

public class LoginResponse {
    private Long id;
    private String cargo;

    public LoginResponse(Long id, String cargo) {
        this.id = id;
        this.cargo = cargo;
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