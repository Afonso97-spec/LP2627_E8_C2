package com.upt.lp.perdidosapi.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.upt.lp.perdidosapi.entity.Categoria;
import com.upt.lp.perdidosapi.entity.Marca;
import com.upt.lp.perdidosapi.entity.Registo;
import com.upt.lp.perdidosapi.entity.SubCategoria;
import com.upt.lp.perdidosapi.entity.Utilizador;
import com.upt.lp.perdidosapi.entityDTOs.RegistoDTO;
import com.upt.lp.perdidosapi.mapper.RegistoMapper;
import com.upt.lp.perdidosapi.repository.CategoriaRepository;
import com.upt.lp.perdidosapi.repository.MarcaRepository;
import com.upt.lp.perdidosapi.repository.RegistoRepository;
import com.upt.lp.perdidosapi.repository.SubcategoriaRepository;
import com.upt.lp.perdidosapi.repository.UtilizadorRepository;

@Service
@Transactional(readOnly=true)

public class RegistoService {
	
	private final RegistoRepository repository;
	private final UtilizadorRepository utilizadorRepository;
    private final CategoriaRepository categoriaRepository;
    private final SubcategoriaRepository subcategoriaRepository;
    private final MarcaRepository marcaRepository;
	
    public RegistoService(RegistoRepository repository, UtilizadorRepository utilizadorRepository,
            				CategoriaRepository categoriaRepository, SubcategoriaRepository subcategoriaRepository,
            				MarcaRepository marcaRepository) {
    		
    	this.repository = repository;
    	this.utilizadorRepository = utilizadorRepository;
    	this.categoriaRepository = categoriaRepository;
    	this.subcategoriaRepository = subcategoriaRepository;
    	this.marcaRepository = marcaRepository;
	
    }
    
    public List<RegistoDTO> listar(){
    	
    	return repository.findAll().stream().map(RegistoMapper::toDTO).toList();

    }
    
    public RegistoDTO obterRegisto(long id) {
    	return RegistoMapper.toDTO(procurarRegisto(id));

    }
    
    @Transactional
    public RegistoDTO criarRegisto(RegistoDTO dto) {
    	
    	validarRegisto(dto);
    	
    	Registo registo = RegistoMapper.toEntity(dto);
    	associarEntidades(registo,dto);
    	
    	return RegistoMapper.toDTO(repository.save(registo));
    	
    }
    
    @Transactional
    public RegistoDTO atualizarDTO(long id, RegistoDTO dto) {
        Registo registo = procurarRegisto(id);
        validarRegisto(dto);

        RegistoMapper.atualizarCampos(dto, registo);
        associarEntidades(registo, dto);

        Registo guardado = repository.save(registo);
        return RegistoMapper.toDTO(guardado);
        
    }
    
    @Transactional
    public void eliminarRegisto(long id) {
    	
    	Registo registo = procurarRegisto(id);
    	repository.delete(registo);
    	
    }
    
    private Registo procurarRegisto(long id) {
    	
    	Registo registo = repository.findById(id).orElse(null);
    	
    	if(registo ==null ) {
    		
    		throw inexistente("Registo", id);
    		
    	}
    	
    	return registo;
    	
    }
    
    private void validarRegisto(RegistoDTO dto) {
        if (dto.utilizadorId() == null || dto.categoriaId() == null || dto.titulo() == null || dto.titulo().isBlank()
                || dto.descricao() == null || dto.descricao().isBlank() || dto.data() == null || dto.estado() == null) {
        	
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, 
            									"Os campos utilizadorId, categoriaId, titulo, descricao, "
            									+ "data e estado são obrigatórios.");
        }
    }
    
    private void associarEntidades(Registo registo, RegistoDTO dto) {
    	
        Utilizador utilizador = utilizadorRepository.findById(dto.utilizadorId()).orElse(null);

        if (utilizador == null) {
        	
            throw inexistente("Utilizador", dto.utilizadorId());
            
        }

        Categoria categoria = categoriaRepository.findById(dto.categoriaId()).orElse(null);

        if (categoria == null) {
        	
            throw inexistente("Categoria", dto.categoriaId());
            
        }

        SubCategoria subcategoria = null;

        if (dto.subcategoriaId() != null) {
        	
        	subcategoria = subcategoriaRepository.findById(dto.subcategoriaId()).orElse(null);

            if (subcategoria == null) {
            	
                throw inexistente("Subcategoria", dto.subcategoriaId());
                
            }

            if (subcategoria.getCategoria().getId() != categoria.getId()) {
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST,
                        "A subcategoria indicada não pertence à categoria escolhida.");
            }
        }

        Marca marca = null;

        if (dto.marcaId() != null) {
        	
            marca = marcaRepository.findById(dto.marcaId()).orElse(null);

            if (marca == null) {
            	
                throw inexistente("Marca", dto.marcaId());
            }
        }

        registo.setUtilizador(utilizador);
        registo.setCategoria(categoria);
        registo.setSubcategoria(subcategoria);
        registo.setMarca(marca);
        
    }
    
    private ResponseStatusException inexistente(String entidade, long id) {
        
    	return new ResponseStatusException(HttpStatus.NOT_FOUND,entidade + " inexistente: " + id);
    	
    }
    
}
