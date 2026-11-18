package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {}
