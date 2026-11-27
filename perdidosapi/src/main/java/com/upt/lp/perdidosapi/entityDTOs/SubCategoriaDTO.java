package com.upt.lp.perdidosapi.entityDTOs;

import java.util.List;

import com.upt.lp.perdidosapi.entity.Categoria;
import com.upt.lp.perdidosapi.entity.Registo;

public record SubCategoriaDTO(int id, String nome, Categoria pertence, List<Registo> lista) {

}
