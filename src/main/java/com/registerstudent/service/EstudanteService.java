package com.registerstudent.service;

import java.util.List;

import com.registerstudent.model.Estudante;

public interface EstudanteService {

	Estudante seve(Estudante estudante);

	Estudante findById(Integer matricula);

	Estudante update(Estudante estudante);

	List<Estudante> findAll();

	void delete(Integer matricula);

	List<Estudante> filterAll(Estudante estudante);

}
