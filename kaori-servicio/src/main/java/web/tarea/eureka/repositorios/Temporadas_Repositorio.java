package web.tarea.eureka.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import web.tarea.eureka.entidades.Temporada; 

public interface Temporadas_Repositorio extends CrudRepository<Temporada, String>{
	@Override
	List<Temporada> findAll();
	List<Temporada> findAllBySerie_idSerie(String idSerie);
}
