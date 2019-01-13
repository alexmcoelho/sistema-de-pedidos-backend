package com.alex.cursospringudemy.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alex.cursospringudemy.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	//não necessita ser envolvida com uma transação de banco de dados, isso faz ela ficar mais rápida e diminui o que se chama de looking do gerenciamento de transações de banco de dados (readOnly=true)
	@Transactional(readOnly=true) 
	Cliente findByEmail(String email);

}
