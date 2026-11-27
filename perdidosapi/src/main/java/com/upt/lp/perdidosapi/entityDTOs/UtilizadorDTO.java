package com.upt.lp.perdidosapi.entityDTOs;

public class UtilizadorDTO {

    private final Long codigo;
    private final String nome;
    private final String email;

    public UtilizadorDTO(Long codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

}
