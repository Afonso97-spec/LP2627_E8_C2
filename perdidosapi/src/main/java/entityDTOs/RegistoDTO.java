package entityDTOs;

import java.time.LocalDate;
import java.time.LocalTime;

import entity.Estado;

public record RegistoDTO( Long id,Long utilizadorId,String titulo,String fotografia,
							String descricao,Long categoriaId,Long subcategoriaId,
							Long marcaId,String local,LocalDate data,LocalTime hora,
							Estado estado
) {}
