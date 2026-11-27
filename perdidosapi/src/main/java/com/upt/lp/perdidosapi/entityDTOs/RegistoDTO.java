package com.upt.lp.perdidosapi.entityDTOs;

import java.time.LocalDate;
import java.time.LocalTime;

import com.upt.lp.perdidosapi.entity.Estado;

public record RegistoDTO(
        Long id,
        Long utilizadorId,
        String titulo,
        String descricao,
        Long categoriaId,
        Long subcategoriaId,
        Long marcaId,
        String local,
        LocalDate data,
        LocalTime hora,
        Estado estado
) {}
