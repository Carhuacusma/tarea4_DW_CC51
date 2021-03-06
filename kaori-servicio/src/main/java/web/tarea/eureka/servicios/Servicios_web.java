package web.tarea.eureka.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.tarea.eureka.entidades.Articulo;
import web.tarea.eureka.entidades.Capitulo;
import web.tarea.eureka.entidades.Serie;
import web.tarea.eureka.entidades.Snippet;
import web.tarea.eureka.entidades.Temporada;
import web.tarea.eureka.entidades.Usuario;
import web.tarea.eureka.repositorios.Articulo_Repositorio;
import web.tarea.eureka.repositorios.AvanceSerie_Repositorio;
import web.tarea.eureka.repositorios.Capitulo_Repositorio;
import web.tarea.eureka.repositorios.Personaje_Repositorio;
import web.tarea.eureka.repositorios.Serie_Repositorio;
import web.tarea.eureka.repositorios.Snippet_Repositorio;
import web.tarea.eureka.repositorios.Temporadas_Repositorio;
import web.tarea.eureka.repositorios.Usuario_Repositorio;

@Service
public class Servicios_web {
	// todos los repositorios;
	@Autowired
	Articulo_Repositorio articulo_Repositorio;
	@Autowired
	AvanceSerie_Repositorio  avanceSerie_Repositorio;
	//AvanceSerieKey_Respositorio avanceSerieKey_Respositorio;
	@Autowired
	Capitulo_Repositorio capitulo_Repositorio;
	@Autowired
	Personaje_Repositorio personaje_Repositorio;
	@Autowired
	Serie_Repositorio serie_Repositorio;
	@Autowired
	Snippet_Repositorio snippet_Repositorio;
	@Autowired
	Temporadas_Repositorio temporadas_Repositorio;
	@Autowired
	Usuario_Repositorio usuario_Repositorio;
	
	//AUXILIAR
	public static Boolean similares(String s1, String s2) { //S1: Original | S2: Busqueda
	    if(s1 == s2) { return true; }
	    s1 = s1.toLowerCase();
	    s2 = s2.toLowerCase();
	    int iguales = 0;
	    int umbral = s1.length()*(3/5);
	    for(int i = 0; i < s1.length(); i++) {
	    	for(int j = 0; j < s2.length(); j++) {
	    		if(s1.charAt(i) == s2.charAt(j)) { iguales++; }
	    		else if (s1.charAt(i) != s2.charAt(j)) {
	    			if(iguales > umbral) { return true; }
	    		}
	    	}
	    }
	    return false;
	}
	
	//--------------------------------------------------------------------
	//FUNCIONES DE LA PAGINA Y ENTIDADES

	
//SNIPPET
	
	public Snippet registrarSnippet(Snippet snippet) {
		Integer nSnippets = snippet.getCapitulo().getSpoilers().size() + 1;
		String nombreSerie;
		Articulo articulo = new Articulo();
		
		//dandole su id
		snippet.setIdSnippet(snippet.getCapitulo().getIdCapitulo() + "SNP" + nSnippets );
		
		//obtenemos el nombre para encontrar el articulo correspondiente
		nombreSerie = snippet.getCapitulo().getTemporada().getSerie().getNombre();
		articulo = findArticuloBySerie(nombreSerie);
		articulo.getSpoilers().add(snippet);
		
		
		articulo_Repositorio.save(articulo);
		
		//articulo.getSpoilers().clear();
		
		
		return snippet_Repositorio.save(snippet);
	}	
	
	public List<Snippet> listarSnippet(){ //deberiamos poner un condicionl para que solo admins tengan acceso
		return this.snippet_Repositorio.findAll();
	}

	
//USUARIO

	@Transactional
	public Usuario registrarUsuario(Usuario nuevo) {
		return usuario_Repositorio.save(nuevo);
	}
	//ESTRICTO
	public Usuario buscarUsuarioNombre(String nombre){
		List<Usuario> todos = this.usuario_Repositorio.findAll();
		for(Usuario usuario:todos) {
			if (usuario.getNombreUsuario() == nombre) { return usuario; }
		}
		return null;
	}
	public Usuario buscarUsuarioId(Long idUsuario) {
		return usuario_Repositorio.findById(idUsuario).get();
	}
	public List<Usuario> obtenerUsuarios(){
		return this.usuario_Repositorio.findAll();
	}
	
// ARTICULOS
	
	@Transactional
	public Articulo registrarArticulo(Articulo nuevo) {
		return articulo_Repositorio.save(nuevo);
	}

	public List<Articulo> obtenerArticulos(){
		return articulo_Repositorio.findAll();
	}
	public Articulo obtenerArticulo(Long id){
		Optional<Articulo> aux = this.articulo_Repositorio.findById(id);
		return aux.get();
	}
	/*
	public List<Snippet> filtrarArticulo(Long idArticulo,String idCapitulo){
		Articulo articulo = new Articulo();
		articulo = obtenerArticulo(idArticulo);
		Capitulo capitulo = capitulo_Repositorio.findById(idCapitulo).get();
		return articulo.getArticuloCensura(capitulo);
	}
	*/
	//EN OPERACION
	public List<Snippet> filtrarArticulo(Long idArticulo, Integer nCap){
		Articulo articulo = new Articulo();
		articulo = obtenerArticulo(idArticulo);
		return articulo.getArticuloCensuraInt(nCap);
		
	}
	
	
	public Articulo findArticuloBySerie(String nombreSerie) {
		List<Articulo> articulos = articulo_Repositorio.findAll();
		for (int i = 0; i < articulo_Repositorio.count();i++) {
			if(articulos.get(i).getSerie().getNombre() == nombreSerie) {
				return articulos.get(i);
			}
		}
		return null;
	}
	
	//EN TEORÍA LA DE SEARCH BAR
	public List<Articulo> obtenerArticulo(String busqueda) {
		String[] aux = busqueda.split(" ");
		List<Articulo> articulos = this.obtenerArticulos();
		List<Articulo> res = new ArrayList<Articulo>();
		for(Articulo auxArt:articulos) {
			if(auxArt.getTituloArticulo() == busqueda || auxArt.getSerie().getNombre() == busqueda){
				res.add(auxArt);
			}else{
				for(short i = 0; i < aux.length;i++) {
					if (auxArt.getSerie().getNombre() == aux[i]) { res.add(auxArt); }
				}
			}
		}
		return articulos;
	}

//AVANCESERIES
	
	public void actualizarAvanceSerie(Usuario usuario, Serie serie, Integer temporada, Integer capitulo) {
		usuario.ActualizarCapitulo(serie, temporada, capitulo);
	}
	
//SERIES
	
	//TODO: FORMULARIOS EN FRONT
	public Serie registrarSerie(Serie nuevo) {
		return serie_Repositorio.save(nuevo);
	}
	
	public List<Serie> listSeries(){
		return (List<Serie>) serie_Repositorio.findAll();
	}
	
	public Serie buscarSerieTitulo(String titulo) {
		//TODO: APRENDER QUERYS
		List<Serie> series = (List<Serie>) serie_Repositorio.findAll();
		for(Serie aux:series) {
			if(this.similares(aux.getNombre(),titulo)) {
				return aux;
			}
		}
		return null;
	}
	
	public Serie buscarSerieId(String idSerie) {
		return serie_Repositorio.findById(idSerie).get();
	}
	
//TEMPORADA
	public List<Temporada> listarTemporada(){
		return temporadas_Repositorio.findAll();
	}
	public Temporada buscarTemporadaId(String idTemporada) {
		return temporadas_Repositorio.findById(idTemporada).get();
	}
	
	public void addTemporada(Serie serie, Temporada temporada) {
		serie.addTemporada(temporada);
	}
	
	public Temporada registrarTemporada(Temporada temporada) {
		return temporadas_Repositorio.save(temporada);
	}
	
	public void addCapitulo(Serie serie, Integer temporada, Capitulo nuevo) {
		serie.getTemporada(temporada).addCapitulo(nuevo);
	}
	
	public List<Temporada> listTemporadasDeSerie(String idSerie){
		return (List<Temporada>) temporadas_Repositorio.findAllBySerie_idSerie(idSerie);
	}
	
//CAPITULOS
	
	public Capitulo registrarCapitulo(Capitulo capitulo) {
		return capitulo_Repositorio.save(capitulo);
	}
	public Capitulo buscarCapituloId(String idCapitulo) {
		return capitulo_Repositorio.findById(idCapitulo).get();
	}
	public List<Capitulo> buscarCapituloId2(String idCapitulo) {
		return (List<Capitulo>) capitulo_Repositorio.findAllByidCapitulo(idCapitulo);
	}
	public List<Capitulo> listarCapitulo(){
		return (List<Capitulo>) capitulo_Repositorio.findAll();
	}
	
	public List<Capitulo> listCapitulosDeTemporada(String idTemporada){
		return (List<Capitulo>) capitulo_Repositorio.findAllByTemporada_idTemporada(idTemporada);
	}
}


