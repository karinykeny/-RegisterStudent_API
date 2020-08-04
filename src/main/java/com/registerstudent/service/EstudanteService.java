package com.registerstudent.service;

import java.util.List;

import com.registerstudent.modal.Estudante;

public interface EstudanteService {

	Estudante seve(Estudante estudante);

	Estudante findById(Integer matricula);

	Estudante update(Estudante estudante);

	List<Estudante> findAll();

}
