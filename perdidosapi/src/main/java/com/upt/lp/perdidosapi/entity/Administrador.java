package com.upt.lp.perdidosapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrador")
public class Administrador {

    @Id
    private Long codigoFuncionario;

    protected Administrador() {
    }

    public Administrador(Long codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public Long getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(Long codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }
}