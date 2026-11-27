package controller;

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

import entityDTOs.RegistoDTO;
import service.RegistoService;

@RestController
@RequestMapping("/api/registos")

public class RegistoController {

    private final RegistoService service;

    public RegistoController(RegistoService service) {
    	
        this.service = service;
        
    }

    @GetMapping
    public List<RegistoDTO> listar() {
        return service.listar();
        
    }

    @GetMapping("/{id}")
    public RegistoDTO obter(@PathVariable("id") long id) {
    	
        return service.obterRegisto(id);
        
    }

    @PostMapping
    public ResponseEntity<RegistoDTO> criar(@RequestBody RegistoDTO dto) {
        RegistoDTO criado = service.criarRegisto(dto);

        URI localizacao = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(criado.id())
                			.toUri();

        return ResponseEntity.created(localizacao).body(criado);
        
    }

    @PutMapping("/{id}")
    
    public RegistoDTO atualizar(@PathVariable("id") long id, @RequestBody RegistoDTO dto) {
    	
        return service.atualizarDTO(id, dto);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") long id) {
    	
        service.eliminarRegisto(id);
        return ResponseEntity.noContent().build();
        
    }
}