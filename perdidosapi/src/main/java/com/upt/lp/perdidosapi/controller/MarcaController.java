package com.upt.lp.perdidosapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.upt.lp.perdidosapi.entityDTOs.MarcaDTO;
import com.upt.lp.perdidosapi.service.MarcaService;

@RestController
@RequestMapping("/api/marcas")

public class MarcaController {

    private final MarcaService service;

    public MarcaController(MarcaService service) {
    	
        this.service = service;
        
    }

    @GetMapping
    public List<MarcaDTO> listar() {
        return service.listar();
        
    }

    @GetMapping("/{id}")
    public MarcaDTO obter(@PathVariable("id") long id) {
    	
        return service.obterMarca(id);
        
    }

    @PostMapping
    public ResponseEntity<MarcaDTO> criar(@RequestBody MarcaDTO dto) {
        MarcaDTO criada = service.criarMarca(dto);

        URI localizacao = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(criada.id())
                			.toUri();

        return ResponseEntity.created(localizacao).body(criada);
        
    }

    @PutMapping("/{id}")
    
    public MarcaDTO atualizar(@PathVariable("id") long id, @RequestBody MarcaDTO dto) {
    	
        return service.atualizarMarca(id, dto);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") long id) {
    	
        service.eliminarMarca(id);
        return ResponseEntity.noContent().build();
        
    }
}