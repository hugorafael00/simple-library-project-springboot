package com.hr.libdemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hr.libdemo.domain.Emprestimo;

@Repository
public interface EmpRepository extends MongoRepository<Emprestimo, String> {

}
