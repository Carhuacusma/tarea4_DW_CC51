package web.tarea.eureka.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Capitulos")
public class Capitulo {
	@Id
	@Column(name = "id")
	private String idCapitulo;
	private int numCap;
	private String tituloCap;
	private String temporada;
	
	@Cascade(CascadeType.ALL)
	@JsonIgnore
	@OneToMany(mappedBy = "capitulo")
	List<Snippet> spoilers;
	
	public Capitulo() {
		this.spoilers = new ArrayList<Snippet>();
	}
	
	//GET & SET
	public String getIdCapitulo() {
		return idCapitulo;
	}
	public void setIdCapitulo(String idCapitulo) {
		this.idCapitulo = idCapitulo;
	}

	public int getNumCap() {
		return numCap;
	}
	public void setNumCap(int numCap) {
		this.numCap = numCap;
	}

	public String getTituloCap() {
		return tituloCap;
	}
	public void setTituloCap(String tituloCap) {
		this.tituloCap = tituloCap;
	}
/*
	public Temporada getTemporada() {
		return temporada;
	}
	public void setTemporada(Temporada temporada) {
		this.temporada = temporada;
	}
*/
	public String getTemporada() {
		return temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	public List<Snippet> getSpoilers() {
		return spoilers;
	}

	public void setSpoilers(List<Snippet> spoilers) {
		this.spoilers = spoilers;
	}
	
	//MAYOR O MENOR (TODO: AVERIGUAR SOBRE SOBRECARGA DE OPERADORES EN JAVA)
/*
	public boolean mayorA(Capitulo otro) {
		return (this.getTemporada().getNumTemporada() > otro.getTemporada().getNumTemporada());
	}
	public boolean menorA(Capitulo otro) {
		return (this.getTemporada().getNumTemporada() < otro.getTemporada().getNumTemporada());
	}	
*/
}
