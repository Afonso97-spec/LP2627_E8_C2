package com.upt.lp.perdidosapi.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.upt.lp.perdidosapi.entityDTOs.CriarUtilizadoresDTO;
import com.upt.lp.perdidosapi.entityDTOs.UtilizadorDTO;
import com.upt.lp.perdidosapi.service.UtilizadorService;

@RestController //Diz ao Spring que esta classe vai responder a pedidos web e que os dados devolvidos devem ser convertidos automaticamente para JSON.
@RequestMapping("/api/utilizadores") // Define o endereço base (URL) para todos os métodos desta classe.
public class UtilizadorController {

    private final UtilizadorService service; // logica do service

    public UtilizadorController(UtilizadorService service) {
        this.service = service;
    }

    @PostMapping //responde aos metodos http POST.
    public ResponseEntity<UtilizadorDTO> registar(
            @RequestBody CriarUtilizadoresDTO dto) { //Pega no corpo JSON enviado pelo Postman e converte-o automaticamente CriarUtilizadorDTO.

        UtilizadorDTO criado = service.registarUtilizador(dto);

        URI localizacao = ServletUriComponentsBuilder //Cria o link exato onde o novo utilizador pode ser consultado no futuro
                .fromCurrentRequestUri()
                .path("/{codigo}")
                .buildAndExpand(criado.codigo())
                .toUri();

        return ResponseEntity.created(localizacao).body(criado); //Devolve uma resposta HTTP
    }

    @GetMapping("/{codigo}") //Este método responde a pedidos HTTP GET
    public UtilizadorDTO obter(@PathVariable("codigo") long codigo) { //Extrai o número que vem no próprio URL e coloca-o na variável codigo
        return service.obterUtilizador(codigo);
    }
}