package entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false, unique = true)
    private String nome;
    
    @OneToMany(mappedBy = "pertence")
    private List<SubCategoria> subCategorias = new ArrayList<>();

    @OneToMany(mappedBy = "categoria")
    private List<Registo> registos = new ArrayList<>();
    
    // Construtores:
    
    protected Categoria() {
    }

    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    //Getters:
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<SubCategoria> getSubCategorias() {
        return subCategorias;
    }

    public List<Registo> getRegistos() {
        return registos;
    }
    
    // Setters:
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /** 
     * Adiciona uma subcategoria a esta categoria, se nao pertencer à categoria.
     * @param subCategoria
     */
    public void adicionarSubCategoria(SubCategoria subCategoria) {
    	
        if (!subCategorias.contains(subCategoria)) {
        	
            subCategorias.add(subCategoria);
            subCategoria.setCategoria(this);
        }
    }

    /** 
     * Remove uma subcategoria desta categoria.
     * @param subCategoria
     */
    public void removerSubCategoria(SubCategoria subCategoria) {
    	
        subCategorias.remove(subCategoria);
        subCategoria.setCategoria(null);
    }

    /**
     * Adiciona um registo, se nao estiver na lista.
     * @param registo
     */
    public void adicionarRegisto(Registo registo) {
    	
        if (!registos.contains(registo)) {
        	
            registos.add(registo);
            registo.setCategoria(this);
        }

    }

    /**
     * Remove um registo da lista de registos desta categoria.
     * @param registo
     */
    public void removerRegisto(Registo registo) {
    	
        registos.remove(registo);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nome;
    }
}
