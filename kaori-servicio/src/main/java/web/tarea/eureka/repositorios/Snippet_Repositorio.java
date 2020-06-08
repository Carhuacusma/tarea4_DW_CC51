package web.tarea.eureka.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import web.tarea.eureka.entidades.Snippet;

public interface Snippet_Repositorio extends CrudRepository<Snippet, String>{
	@Override
	List<Snippet> findAll();
}
