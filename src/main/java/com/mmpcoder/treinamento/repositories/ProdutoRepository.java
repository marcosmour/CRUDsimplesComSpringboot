package com.mmpcoder.treinamento.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmpcoder.treinamento.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID>{

}
