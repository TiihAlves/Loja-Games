package com.generation.lojagames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.lojagames.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

	public List<Produtos> findAllByPrecoGreaterThanEqual(@Param("preco") Double preco);
}
