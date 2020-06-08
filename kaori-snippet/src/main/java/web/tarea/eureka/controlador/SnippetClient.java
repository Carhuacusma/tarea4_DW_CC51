package web.tarea.eureka.controlador;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import web.tarea.eureka.modelo.Capitulo;

@FeignClient(name = "servicio-kaori", url="localhost:8001")
public interface SnippetClient {
	
	@GetMapping("/listCapitulo")
	public List<Capitulo> listCapitulo();
	@RequestMapping("/listCapitulosByIdTemporada/{idTemporada}")
	public List<Capitulo> listCapitulosByIdTemporada(@PathVariable(value="idTemporada") String idTemporada);
	@RequestMapping("/buscarCapituloById/{idCapitulo}")
	public List<Capitulo> buscarCapituloById(@PathVariable(value="idCapitulo") String idCapitulo);
	
	/*
	@GetMapping("/capitulos")
	public List<Capitulo> getCapitulos();
	@GetMapping("capitulos/{id}")
	public Capitulo getCapitulobyId(@PathVariable String id);
	*/
}
