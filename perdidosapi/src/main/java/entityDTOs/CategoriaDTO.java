package entityDTOs;

import java.util.List;
import entity.Registo;
import entity.SubCategoria;

public record CategoriaDTO(int id, String nome, List<SubCategoria> subCategorias, List<Registo> registos) {

}
