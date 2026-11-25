package entityDTOs;

import java.util.List;
import entity.Categoria;
import entity.Registo;

public record SubCategoriaDTO(int id, String nome, Categoria pertence, List<Registo> lista) {

}
