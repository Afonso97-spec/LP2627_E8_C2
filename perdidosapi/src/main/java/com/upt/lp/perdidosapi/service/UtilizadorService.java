package com.upt.lp.perdidosapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import entity.Utilizador;
import entityDTOs.UtilizadorDTO;
import repository.UtilizadorRepository;

@Service
public class UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;

    public UtilizadorService(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }

    public UtilizadorDTO registarUtilizador(String nome, String email, String password) {
        if (nome == null || nome.isBlank()
                || email == null || email.isBlank()
                || password == null || password.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nome, email e palavra-passe são obrigatórios.");
        }

        String emailLimpo = email.trim();

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

        Utilizador novoUtilizador = new Utilizador(nome.trim(), emailLimpo, password);
        Utilizador utilizadorGuardado = utilizadorRepository.save(novoUtilizador);

        return new UtilizadorDTO(
                utilizadorGuardado.getCodigo(),
                utilizadorGuardado.getNome(),
                utilizadorGuardado.getEmail());
    }
}
