package com.upt.lp.perdidosapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.upt.lp.perdidosapi.entity.Utilizador;
import com.upt.lp.perdidosapi.entityDTOs.CriarUtilizadoresDTO;
import com.upt.lp.perdidosapi.entityDTOs.UtilizadorDTO;
import com.upt.lp.perdidosapi.mapper.UtilizadorMapper;
import com.upt.lp.perdidosapi.repository.UtilizadorRepository;

@Service
public class UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;

    public UtilizadorService(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }

    public UtilizadorDTO registarUtilizador(CriarUtilizadoresDTO dto) { //Este método valida os dados recebidos, verifica duplicados e guarda o novo utilizador
        if (dto.nome() == null || dto.nome().isBlank()
                || dto.email() == null || dto.email().isBlank()
                || dto.password() == null || dto.password().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nome, email e palavra-passe são obrigatórios.");
        }

        String emailLimpo = dto.email().trim();

        if (!emailLimpo.contains("@")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O email introduzido não é válido.");
        }

        if (utilizadorRepository.existsByEmailIgnoreCase(emailLimpo)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe uma conta com esse email.");
        }

        Utilizador novoUtilizador = UtilizadorMapper.toEntity(dto);
        Utilizador guardado = utilizadorRepository.save(novoUtilizador);

        return UtilizadorMapper.toDTO(guardado);
    }

    public UtilizadorDTO obterUtilizador(long codigo) { //Este método procura um utilizador específico pelo seu ID 
        Utilizador utilizador = utilizadorRepository.findById(codigo).orElse(null);

        if (utilizador == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Utilizador inexistente: " + codigo);
        }

        return UtilizadorMapper.toDTO(utilizador);
    }
}
