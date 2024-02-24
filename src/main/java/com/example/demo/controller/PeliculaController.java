package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Genero;
import com.example.demo.model.Pelicula;
import com.example.demo.service.GeneroService;
import com.example.demo.service.PeliculaService;

@Controller
@RequestMapping("/pelicula")
public class PeliculaController {
	
	@Autowired
	private PeliculaService peliculaService;
	
	@Autowired
	private GeneroService generoService;

	@GetMapping("/peliculas")
	public String getAllPeliculas(Model model) {
		List<Pelicula> lisPeliculas = peliculaService.getAllPeliculas();
		model.addAttribute("peliculas", lisPeliculas);
        return "peliculaList";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("generos", generoService.getAllGeneros());
		return "peliculaRegister";
	}
	
	
	@PostMapping("/register")
	public String createPelicula(@RequestParam("name") String name, 
			@RequestParam("director") String director,
			@RequestParam("fechaestreno") String fechaestreno,
			@RequestParam("id") Long id, Model model) {
		
		Pelicula pelicula = new Pelicula();
		pelicula.nombre = name;
		pelicula.director = director;
		pelicula.fechaestreno = fechaestreno;
		
		Genero genero = generoService.getGeneroById(id);

		pelicula.genero = genero;
		
		peliculaService.createPelicula(pelicula);
		
		model.addAttribute("peliculas", peliculaService.getAllPeliculas());
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "peliculaList";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		
		Pelicula pelicula = peliculaService.getPeliculaById(id);
		
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "peliculaEdit";
	}
	
	
	@PostMapping("/edit")
	public String createProduct(@RequestParam("id") Long id, @RequestParam("name") String name, 
			@RequestParam("director") String director,
			@RequestParam("fechaestreno") String fechaestreno,
			@RequestParam("idgenero") Long idgenero, Model model) {
		
		Pelicula pelicula = peliculaService.getPeliculaById(id);
		pelicula.nombre = name;
		pelicula.director = director;
		pelicula.fechaestreno = fechaestreno;
		
		Genero genero = generoService.getGeneroById(idgenero);

		pelicula.genero = genero;
		
		peliculaService.createPelicula(pelicula);
		
		model.addAttribute("peliculas",peliculaService.getAllPeliculas());
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "peliculaList";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePelicula(@PathVariable Long id, Model model) {
		peliculaService.deletePelicula(id);
		
		model.addAttribute("peliculas", peliculaService.getAllPeliculas());
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "peliculaList";
	}
	
	
	
}


