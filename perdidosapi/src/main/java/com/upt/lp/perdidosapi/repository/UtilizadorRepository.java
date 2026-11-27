package com.upt.lp.perdidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upt.lp.perdidosapi.entity.Utilizador;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {

    boolean existsByEmailIgnoreCase(String email);
}