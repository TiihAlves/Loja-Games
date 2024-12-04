package com.generation.lojagames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojagames.model.Categorias;
import com.generation.lojagames.repository.CategoriasRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriasController {

	@Autowired
	private CategoriasRepository categoriasRepository;
	
	@GetMapping
	public ResponseEntity<List<Categorias>> getAll(){
		return ResponseEntity.ok(categoriasRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categorias> getById(@PathVariable Long id){
		return categoriasRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/categorias/{categoria}")
	public ResponseEntity<List<Categorias>> getByCateg(@PathVariable String categoria){
		return ResponseEntity.ok(categoriasRepository.findAllByCategoriaContainingIgnoreCase(categoria));
	}
	
	@PostMapping
	public ResponseEntity<Categorias> post(@Valid @RequestBody Categorias categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriasRepository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categorias> put(@Valid @RequestBody Categorias categoria){
		return ResponseEntity.status(HttpStatus.OK).body(categoriasRepository.save(categoria));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Categorias> categoria = categoriasRepository.findById(id);

		if (categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		categoriasRepository.deleteById(id);
	}
	
}
