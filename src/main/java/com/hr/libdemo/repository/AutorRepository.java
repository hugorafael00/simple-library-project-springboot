package com.hr.libdemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hr.libdemo.domain.Autor;

@Repository
public interface AutorRepository extends MongoRepository<Autor, String> {
}
