package repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import entity.Registo;

public interface RegistoRepository extends JpaRepository<Registo, Integer>{
	
	List<Registo> findAllByOrderByDataDescIdDesc();
}
