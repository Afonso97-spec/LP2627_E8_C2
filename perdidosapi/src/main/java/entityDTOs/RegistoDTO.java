package entityDTOs;

import java.time.LocalDate;
import java.time.LocalTime;
import entity.Estado;

public record RegistoDTO(Integer id, Integer utilizadorId, String titulo,
	     					String descricao, Integer categoriaId,
	     					Integer subcategoriaId, Integer marcaId, String local,
	     					LocalDate data, LocalTime hora, Estado estado) {

}
