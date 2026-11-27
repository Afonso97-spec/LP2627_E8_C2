package com.upt.lp.perdidosapi.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.upt.lp.perdidosapi.entity.Marca;
import com.upt.lp.perdidosapi.entityDTOs.MarcaDTO;
import com.upt.lp.perdidosapi.mapper.MarcaMapper;
import com.upt.lp.perdidosapi.repository.MarcaRepository;

@Service
@Transactional(readOnly=true)

public class MarcaService {
	
	private final MarcaRepository repository;
	
    public MarcaService(MarcaRepository repository) {
    	
    	this.repository = repository;
	
    }
    
    public List<MarcaDTO> listar(){
    	
    	return repository.findAll().stream().map(MarcaMapper::toDTO).toList();

    }
    
    public MarcaDTO obterMarca(long id) {
    	return MarcaMapper.toDTO(procurarMarca(id));

    }
    
    // US17 - Registar marca
    @Transactional
    public MarcaDTO criarMarca(MarcaDTO dto) {
    	
    	validarMarca(dto);
    	
    	if (repository.existsByNomeIgnoreCase(dto.nome())) {
    		
    		throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma marca com esse nome.");
    		
    	}
    	
    	Marca marca = MarcaMapper.toEntity(dto);
    	
    	return MarcaMapper.toDTO(repository.save(marca));
    	
    }
    
    // US16 - Editar marca
    @Transactional
    public MarcaDTO atualizarMarca(long id, MarcaDTO dto) {
        Marca marca = procurarMarca(id);
        validarMarca(dto);
        
        if (repository.existsByNomeIgnoreCaseAndIdNot(dto.nome(), id)) {
        	
        	throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe outra marca com esse nome.");
        	
        }

        MarcaMapper.atualizarCampos(dto, marca);

        Marca guardada = repository.save(marca);
        return MarcaMapper.toDTO(guardada);
        
    }
    
    // US15 - Eliminar marca
    @Transactional
    public void eliminarMarca(long id) {
    	
    	Marca marca = procurarMarca(id);
    	
    	if (!marca.getRegistos().isEmpty()) {
    		
    		throw new ResponseStatusException(HttpStatus.CONFLICT, 
    											"Não é possível eliminar a marca: existem registos associados.");
    		
    	}
    	
    	repository.delete(marca);
    	
    }
    
    private Marca procurarMarca(long id) {
    	
    	Marca marca = repository.findById(id).orElse(null);
    	
    	if(marca == null) {
    		
    		throw inexistente("Marca", id);
    		
    	}
    	
    	return marca;
    	
    }
    
    private void validarMarca(MarcaDTO dto) {
        if (dto.nome() == null || !dto.nome().matches("\\p{L}+")) {
        	
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, 
            									"O campo nome é obrigatório e só pode conter letras.");
        }
    }
    
    private ResponseStatusException inexistente(String entidade, long id) {
        
    	return new ResponseStatusException(HttpStatus.NOT_FOUND,entidade + " inexistente: " + id);
    	
    }
    
}