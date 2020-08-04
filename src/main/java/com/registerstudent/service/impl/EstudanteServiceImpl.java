package com.registerstudent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registerstudent.modal.Estudante;
import com.registerstudent.repository.EstudanteRepository;
import com.registerstudent.service.EstudanteService;

@Service
public class EstudanteServiceImpl implements EstudanteService {
	
	@Autowired
	private EstudanteRepository repository;

	@Override
	public Estudante seve(Estudante estudante) {
		return repository.save(estudante);
	}

}
