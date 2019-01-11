package com.alex.cursospringudemy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alex.cursospringudemy.domain.Pedido;
import com.alex.cursospringudemy.repositories.PedidoRepository;
import com.alex.cursospringudemy.services.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id)  {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
