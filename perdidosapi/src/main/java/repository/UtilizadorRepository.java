package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Utilizador;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long>{}
