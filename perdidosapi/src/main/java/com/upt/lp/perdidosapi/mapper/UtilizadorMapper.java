
package com.upt.lp.perdidosapi.mapper;

import com.upt.lp.perdidosapi.entity.Utilizador;
import com.upt.lp.perdidosapi.entityDTOs.CriarUtilizadoresDTO;
import com.upt.lp.perdidosapi.entityDTOs.UtilizadorDTO;

public class UtilizadorMapper {

    private UtilizadorMapper() { //Isto impede que alguém instancie esta classe 
    }

    public static Utilizador toEntity(CriarUtilizadoresDTO dto) { //Converte os dados recebidos quando alguém quer registar um utilizador
        return new Utilizador(
                dto.nome().trim(), // .trim() -> limpar espaços em branco acidentais 
                dto.email().trim(),
                dto.password());
    }

    public static UtilizadorDTO toDTO(Utilizador utilizador) { //Faz o caminho inverso
        return new UtilizadorDTO(
                utilizador.getCodigo(),
                utilizador.getNome(),
                utilizador.getEmail());
    }
}
