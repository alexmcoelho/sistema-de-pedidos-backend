package com.alex.cursospringudemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.cursospringudemy.domain.Cidade;
import com.alex.cursospringudemy.domain.Cliente;
import com.alex.cursospringudemy.domain.Endereco;
import com.alex.cursospringudemy.domain.enums.TipoCliente;
import com.alex.cursospringudemy.dto.ClienteDTO;
import com.alex.cursospringudemy.dto.ClienteNewDTO;
import com.alex.cursospringudemy.repositories.CategoriaRepository;
import com.alex.cursospringudemy.repositories.ClienteRepository;
import com.alex.cursospringudemy.repositories.EnderecoRepository;
import com.alex.cursospringudemy.services.exceptions.ExceptionDataIntegrityViolation;
import com.alex.cursospringudemy.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	private ClienteRepository repo;
	private EnderecoRepository enderecoRepository;
	private BCryptPasswordEncoder pe;
	
	@Autowired
    public ClienteService(
    		ClienteRepository repo,
    		EnderecoRepository enderecoRepository,
    		BCryptPasswordEncoder pe) {
		this.repo = repo;
		this.enderecoRepository = enderecoRepository;
		this.pe = pe;
	}

	public Cliente find(Integer id)  {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId()); //caso o id não existe lança uma exception
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		try {
			repo.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new ExceptionDataIntegrityViolation("Não é possível excluir um cliente que possuí registros de pedidos");
		}
		
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
