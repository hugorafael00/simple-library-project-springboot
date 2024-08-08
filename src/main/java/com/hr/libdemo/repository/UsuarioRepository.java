package com.hr.libdemo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.hr.libdemo.domain.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    List<Usuario> findByCodigoContainingIgnoreCase(String text);

    @Query("{ 'codigo' : { $regex: ?0, $options: 'i' } }")
    Usuario searchCodigo(String text);

    @Query(value = "{ 'idAcesso' : $0 }", delete = true)
    void deleteByIdAcesso(String idAcesso);

}
