package com.upt.lp.perdidosapi.mapper;

import com.upt.lp.perdidosapi.entity.Marca;
import com.upt.lp.perdidosapi.entityDTOs.MarcaDTO;

public class MarcaMapper {
	
	private MarcaMapper() {
		
	}
	
	public static MarcaDTO toDTO(Marca marca) {
		return new MarcaDTO(marca.getId(), marca.getNome(),
				marca.getRegistos().stream().map(registo -> registo.getId()).toList());
		
	}
	
	public static Marca toEntity(MarcaDTO dto) {
		
		Marca marca = new Marca();
		atualizarCampos(dto, marca);
		return marca;
	}
	
	public static void atualizarCampos(MarcaDTO dto, Marca marca) {
		
		marca.setNome(dto.nome().trim());
	}
}
