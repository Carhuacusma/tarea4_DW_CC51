package web.tarea.eureka.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import web.tarea.eureka.entidades.Usuario;

public interface Usuario_Repositorio extends CrudRepository<Usuario, Long> {
	@Override
    List<Usuario> findAll();
}
