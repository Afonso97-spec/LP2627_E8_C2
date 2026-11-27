package com.upt.lp.perdidosapi.entityDTOs;

import java.util.List;

import com.upt.lp.perdidosapi.entity.Registo;
import com.upt.lp.perdidosapi.entity.SubCategoria;

public record CategoriaDTO(int id, String nome, List<SubCategoria> subCategorias, List<Registo> registos) {

}
