package com.alex.sistemadepedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alex.sistemadepedidos.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Categoria obj WHERE obj.nome LIKE %:nome%")
	List<Categoria> findByNome(@Param("nome") String nome);
}
