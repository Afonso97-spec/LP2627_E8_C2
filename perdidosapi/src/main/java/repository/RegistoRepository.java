package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Registo;

public interface RegistoRepository extends JpaRepository<Registo, Long>{}
