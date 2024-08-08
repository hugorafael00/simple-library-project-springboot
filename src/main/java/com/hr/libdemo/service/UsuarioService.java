package com.hr.libdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.libdemo.domain.Usuario;
import com.hr.libdemo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public Usuario add(Usuario obj) {
        return repo.insert(obj);
    }


    public String remove(String id) {
        repo.deleteByIdAcesso(id);
        return "O Usuário foi removido com sucesso";
    }
}
