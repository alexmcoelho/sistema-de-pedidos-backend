package com.alex.sistemadepedidos.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alex.sistemadepedidos.domain.Cidade;
import com.alex.sistemadepedidos.domain.Estado;
import com.alex.sistemadepedidos.dto.CidadeDTO;
import com.alex.sistemadepedidos.dto.EstadoDTO;
import com.alex.sistemadepedidos.services.CidadeService;
import com.alex.sistemadepedidos.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Estado> list = service.findAll();
		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{estadoId}/cidades", method = RequestMethod.GET)
	public ResponseEntity<?> findCidades(@PathVariable Integer estadoId) {
		List<Cidade> list = cidadeService.findAByEstado(estadoId);
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
}
