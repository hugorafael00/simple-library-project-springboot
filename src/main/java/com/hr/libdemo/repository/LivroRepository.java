package com.hr.libdemo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.hr.libdemo.domain.Livro;

@Repository
public interface LivroRepository extends MongoRepository<Livro, String> {

    @Query("{ 'titulo' : { $regex: ?0, $options: 'i' } }")
    List<Livro> searchTitulo(String text);

    List<Livro> findByTituloContainingIgnoreCase(String text);

    @Query("{ $and: [ { 'ano' : { $gte: ?1 } } , { 'ano' : { $lte: ?2 } }, { $or: [ { 'titulo' : { $regex: ?0, $options: 'i' } }, { 'autor.nome' : { $regex: ?0, $options: 'i' } }, { 'editora' : { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Livro> fullSearch(String text, Integer anoMin, Integer anoMax);
}
