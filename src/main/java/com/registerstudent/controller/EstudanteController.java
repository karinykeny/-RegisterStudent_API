package com.registerstudent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registerstudent.modal.Estudante;
import com.registerstudent.service.EstudanteService;


@RestController
@RequestMapping("/estudantes")
public class EstudanteController {
	
	@Autowired
	private EstudanteService service;
	
	@PostMapping
	public ResponseEntity<Estudante> save(@RequestBody Estudante estudante) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.seve(estudante));
	}
	
	@GetMapping("/{matricula}")
	public ResponseEntity<Estudante> FindByEstudante(@PathVariable Integer matricula) {
		return ResponseEntity.ok(service.findById(matricula));
	}
	
	@PutMapping
	public ResponseEntity<Estudante> updateByEstudante(@RequestBody Estudante estudante) {
		return ResponseEntity.ok(service.update(estudante));
	}
	
	@GetMapping
	public ResponseEntity<List<Estudante>> List() {
		return ResponseEntity.ok(service.findAll());
	}

}
