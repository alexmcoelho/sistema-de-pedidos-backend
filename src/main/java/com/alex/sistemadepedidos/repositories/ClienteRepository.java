package com.alex.sistemadepedidos.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alex.sistemadepedidos.domain.Categoria;
import com.alex.sistemadepedidos.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	//não necessita ser envolvida com uma transação de banco de dados, isso faz ela ficar mais rápida e diminui o que se chama de looking do gerenciamento de transações de banco de dados (readOnly=true)
	@Transactional(readOnly=true) 
	Cliente findByEmail(String email);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Cliente obj WHERE obj.nome LIKE %:nome%")
	List<Cliente> findByNome(@Param("nome") String nome);

}
