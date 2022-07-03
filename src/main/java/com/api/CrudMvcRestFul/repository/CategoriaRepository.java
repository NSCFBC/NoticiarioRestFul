package com.api.CrudMvcRestFul.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.CrudMvcRestFul.model.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long>{

}
