package com.example.demo.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pelicula")
public class Pelicula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(name = "nombre", nullable = false)
	public String nombre;
	
	@Column(name = "director", nullable = false)
	public String director;

	@Column(name = "fechaestreno", nullable = false)
	public String fechaestreno;
	
	@ManyToOne
	@JoinColumn(name = "idgenero", nullable = false)
	public Genero genero;	

}
