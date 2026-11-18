package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{}
