package com.upt.lp.perdidosapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.upt.lp.perdidosapi.entity.Registo;

public interface RegistoRepository extends JpaRepository<Registo, Long>{
	
}
