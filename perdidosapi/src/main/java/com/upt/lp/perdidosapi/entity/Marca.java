package com.upt.lp.perdidosapi.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "marca")
public class Marca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	private String nome;

	@OneToMany(mappedBy = "marca")
	private List<Registo> registos = new ArrayList<>();

	public Marca() {
	}

	public Marca(String nome) {
		this.nome = nome;
	}

	public Marca(long id, String nome) {
		this(nome);
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Registo> getRegistos() {
		return registos;
	}

	public void setRegistos(List<Registo> registos) {
		this.registos = registos;
	}

	@Override
	public String toString() {
		return "Marca [id=" + id + ", nome=" + nome + "]";
	}
}