package com.upt.lp.perdidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upt.lp.perdidosapi.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{
	
	boolean existsByNomeIgnoreCase(String nome);
	
	boolean existsByNomeIgnoreCaseAndIdNot(String nome, Long id);
}
