package com.alex.cursospringudemy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.cursospringudemy.domain.Cidade;
import com.alex.cursospringudemy.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findAByEstado(Integer estadoId){
		return repo.findCidades(estadoId);
	}
	
}
