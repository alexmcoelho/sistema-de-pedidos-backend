package com.alex.cursospringudemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.alex.cursospringudemy.domain.Categoria;
import com.alex.cursospringudemy.repositories.CategoriaRepository;
import com.alex.cursospringudemy.services.exceptions.ExceptionDataIntegrityViolation;
import com.alex.cursospringudemy.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id)  {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId()); //caso o id não existe lança uma exception
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		try {
			repo.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new ExceptionDataIntegrityViolation("Não é possível excluir uma categoria que possuí produtos");
		}
		
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
}
