package service;

import java.util.List;
import entity.Categoria;
import entity.Marca;
import entity.Registo;
import entity.SubCategoria;
import entity.Utilizador;
import entityDTOs.RegistoDTO;
import mapper.RegistoMapper;
import repository.CategoriaRepository;
import repository.MarcaRepository;
import repository.RegistoRepository;
import repository.SubcategoriaRepository;
import repository.UtilizadorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    
    public RegistoDTO obterRegisto(int id) {
    	return RegistoMapper.toDTO(procurarRegisto(id));

    }
    
    @Transactional
    public RegistoDTO criarRegisto(RegistoDTO dto) {
    	
    	validarRegisto(dto);
    	
    	Registo registo = RegistoMapper.toEntity(dto);
    	associarEntidades(registo,dto);
    	
    	return RegistoMapper.toDTO(repository.save(registo));
    	
    	
    }

}
