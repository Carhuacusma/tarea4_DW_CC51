package web.tarea.eureka.repositorios;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import web.tarea.eureka.entidades.Articulo;

public interface Articulo_Repositorio  extends CrudRepository<Articulo, Long>{
	@Override
    List<Articulo> findAll();
}
