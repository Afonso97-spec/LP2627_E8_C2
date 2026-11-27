package com.upt.lp.perdidosapi.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "subcategoria")
public class SubCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, unique = true)
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria pertence;
    
    @OneToMany(mappedBy = "subcategoria")
    private List<Registo> lista = new ArrayList<>();
    
	// Construtores:
    
    protected SubCategoria() {
    }

    public SubCategoria(String nome) {
        this.nome = nome;
    }

    public SubCategoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters:
    
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public Categoria getCategoria() {
        return pertence;
    }

    public List<Registo> getRegistos() {
        return lista;
    }
    
    //Setters:
    
    public void setNome(String novoNome) {
        nome = novoNome;
    }
    
    public void setCategoria(Categoria novo) {
    	pertence = novo;
    }

    /**
     * Adiciona um registo, se nao estiver na lista.
     * @param novo
     */
    public void adicionarRegistoSubCategoria(Registo novo) {
    	if(!lista.contains(novo)) {
    		
    		lista.add(novo);
    		novo.setSubcategoria(this);
    	}
    }
    
    /**
     * Remove um registo da lista de registos desta subcategoria.
     * @param sair
     */
    public void removerRegisto(Registo sair) {
    	lista.remove(sair);
    }

    @Override
    public String toString() {
        return "SubCategoria [ID: " + id + ", Nome: " + nome + "]";
    }
}