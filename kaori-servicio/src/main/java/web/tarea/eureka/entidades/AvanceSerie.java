package web.tarea.eureka.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "AvanceSerie")
public class AvanceSerie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long idAvanceSerie;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "series")
	private Usuario usuario;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuarios")
	private Serie serie;
	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCapitulo", referencedColumnName = "id")
	private Capitulo capitulo;

	public AvanceSerie(Usuario usuario, Serie serie, Capitulo capitulo) {
		this.usuario = usuario;
		this.serie = serie;
		this.capitulo = capitulo;
	}
	
	/*
	public AvanceSerieKey getIdAvanceSerie() {
		return idAvanceSerie;
	}
	public void setIdAvanceSerie(AvanceSerieKey idAvanceSerie) {
		this.idAvanceSerie = idAvanceSerie;
	}*/
	
	public long getIdAvanceSerie() {
		return idAvanceSerie;
	}
	public void setIdAvanceSerie(long idAvanceSerie) {
		this.idAvanceSerie = idAvanceSerie;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Serie getSerie() {
		return serie;
	}
	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Capitulo getCapitulo() {
		return capitulo;
	}
	public void setCapitulo(Capitulo capitulo) {
		this.capitulo = capitulo;
	}
	
}
