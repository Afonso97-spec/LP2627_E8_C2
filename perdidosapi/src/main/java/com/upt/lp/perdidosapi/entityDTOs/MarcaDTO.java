package com.upt.lp.perdidosapi.entityDTOs;

import java.util.List;

public record MarcaDTO(
        Long id,
        String nome,
        List<Long> registoIds
) {}
