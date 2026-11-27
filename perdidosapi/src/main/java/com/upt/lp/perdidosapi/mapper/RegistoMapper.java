package com.upt.lp.perdidosapi.mapper;

import com.upt.lp.perdidosapi.entity.Registo;
import com.upt.lp.perdidosapi.entityDTOs.RegistoDTO;

public class RegistoMapper {
	
	private RegistoMapper() {
		
	}
	
	public static RegistoDTO toDTO(Registo registo) {
	    return new RegistoDTO(registo.getId(),registo.getUtilizador().getCodigo(),
	            				registo.getTitulo(),registo.getDescricao(),(long) registo.getCategoria().getId(),
	            				registo.getSubcategoria() == null ? null : (long) registo.getSubcategoria().getId(),
	            				registo.getMarca() == null ? null : registo.getMarca().getId(),registo.getLocal(),
	            				registo.getData(),registo.getHora(),registo.getEstado());
	    
	}
	
	public static Registo toEntity(RegistoDTO dto) {
		
		Registo registo = new Registo();
		atualizarCampos(dto, registo);
		return registo;
	}
	
	public static void atualizarCampos(RegistoDTO dto, Registo registo) {
		
		registo.setTitulo(dto.titulo().trim());
        registo.setDescricao(dto.descricao().trim());
        registo.setLocal(dto.local());
        registo.setData(dto.data());
        registo.setHora(dto.hora());
        registo.setEstado(dto.estado());
		
	}
}
