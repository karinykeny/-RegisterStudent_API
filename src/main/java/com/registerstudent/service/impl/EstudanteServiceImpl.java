package com.registerstudent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.registerstudent.exceptionhandler.NegocioExcepition;
import com.registerstudent.modal.Estudante;
import com.registerstudent.repository.EstudanteRepository;
import com.registerstudent.service.EstudanteService;

@Service
public class EstudanteServiceImpl implements EstudanteService {
	
	@Autowired
	private EstudanteRepository repository;

	@Override
	public Estudante seve(Estudante estudante) {
		Estudante estudanteExistente = repository.findByCpf(estudante.getCpf());
		
		if (estudanteExistente != null && !estudanteExistente.equals(estudante)) {
			throw new NegocioExcepition("CPF já cadastrado.");
		}
		
		return repository.save(estudante);
	}

	@Override
	public Estudante findById(Integer matricula) {
		Optional<Estudante> estudante = repository.findById(matricula);
		if (! estudante.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return estudante.get();
	}

	@Override
	public Estudante update(Estudante estudante) {
		Estudante updateEstudante = repository.findById(estudante.getMatricula()).get();
		BeanUtils.copyProperties(estudante, updateEstudante, "matricula");
		return repository.save(updateEstudante);
	}

	@Override
	public List<Estudante> findAll() {
		return repository.findAll();
	}

	@Override
	public void delete(Integer matricula) {
		Optional<Estudante> estudante = repository.findById(matricula);
		if (!estudante.isPresent()) {
			throw new NegocioExcepition("Estudante não encontrado.");
		} 
		
		repository.delete(estudante.get());
	}

	@Override
	public List<Estudante> filterAll(Estudante estudante) {	
		if(isNull(estudante)) {
			throw new NegocioExcepition("Parâmetro de pesquisa incorreto.");
		}
		
		return repository.findAll(Example.of(estudante));
	}
	
	private boolean isNull(Estudante estudante) {
		if(estudante.getCpf() == null && estudante.getEmail() == null 
		   && estudante.getMatricula() == null && estudante.getNome() == null
		   && estudante.getSobrenome() == null) {
			
			return true;
		}
		
		return false;
	}

}
