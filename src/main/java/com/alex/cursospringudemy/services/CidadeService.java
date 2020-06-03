package com.alex.cursospringudemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alex.cursospringudemy.domain.Cidade;
import com.alex.cursospringudemy.domain.Cidade;
import com.alex.cursospringudemy.dto.CidadeDTO;
import com.alex.cursospringudemy.repositories.CidadeRepository;
import com.alex.cursospringudemy.services.exceptions.ExceptionDataIntegrityViolation;
import com.alex.cursospringudemy.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findAByEstado(Integer estadoId){
		return repo.findCidades(estadoId);
	}
	
	public Cidade find(Integer id)  {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}
	
	
	public Cidade insert(Cidade obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	
	public void delete(Integer id) {
		try {
			repo.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new ExceptionDataIntegrityViolation("Não é possível excluir um cliente que possuí pedidos");
		}
		
	}
	
	public List<Cidade> findAll(){
		return repo.findAll();
	}
	
	public Page<Cidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cidade fromDTO(CidadeDTO objDTO) {
		return new Cidade(objDTO.getId(), objDTO.getNome(), objDTO.getEstado());
	}
	
}
