package com.upt.lp.perdidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upt.lp.perdidosapi.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}
