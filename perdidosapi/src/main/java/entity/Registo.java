package entity;

import java.time.LocalDate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Registo")

public class Registo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "utilizador_id", nullable = false)
	private Utilizador utilizador;
	
	@Column(nullable=false)
	private String titulo;
	
	@Column(nullable = false)
	private String descricao;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "categoria_id", nullable=false)
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "subcategoria_id", nullable = false)
	private SubCategoria subcategoria; 
	
	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;
	
	private String local;
	
	@Column(nullable = false)
	private LocalDate data;
	
	private LocalTime hora;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Estado estado;
	
	public Registo() {
	}
	
	public Registo(Utilizador utilizador, String titulo, String descricao,
            		Categoria categoria, SubCategoria subcategoria, Marca marca,
            		String local, LocalDate data, Estado estado) {
		
		 this.utilizador = utilizador;
	        this.titulo = titulo;
	        this.descricao = descricao;
	        this.categoria = categoria;
	        this.subcategoria = subcategoria;
	        this.marca = marca;
	        this.local = local;
	        this.data = data;
	        this.estado = estado;
		
	}
	
	public Registo(int id, Utilizador utilizador, String titulo, String descricao,
            Categoria categoria, SubCategoria subcategoria, Marca marca,
            String local, LocalDate data, Estado estado) {
		
			this(utilizador, titulo, descricao, categoria, subcategoria, marca,
					local, data, estado);
			this.id = id;
			
}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public SubCategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(SubCategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Registo [id=" + id + ", utilizador=" + utilizador + ", titulo=" + titulo 
				+ ", descricao=" + descricao + ", categoria=" + categoria + ", subcategoria=" + subcategoria
				+ ", marca=" + marca + ", local=" + local + ", data=" + data + ", hora=" + hora + ", estado=" + estado
				+ "]";
	}
	
	
	
	

}
