package web.tarea.eureka.modelo;

import java.util.List;

import javax.persistence.CascadeType;
//import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Personaje {
	@Id
	private String idPersonaje;
	private String Nombre;
	private String serie;

	@JsonIgnore
	@ManyToMany
	(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "Personaje_Snippet_tlb",
			joinColumns = @JoinColumn(name = "idPersonaje"),
			inverseJoinColumns = @JoinColumn(name = "idSnippet")
	)
	private List<Snippet> snippets;
	
	public String getIdPersonaje() {
		return idPersonaje;
	}

	public void setIdPersonaje(String idPersonaje) {
		this.idPersonaje = idPersonaje;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public List<Snippet> getSnippets() {
		return snippets;
	}

	public void setSnippets(List<Snippet> snippets) {
		this.snippets = snippets;
	}
	
}
