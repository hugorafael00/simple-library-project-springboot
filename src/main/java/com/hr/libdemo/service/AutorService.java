package com.hr.libdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.libdemo.domain.Autor;
import com.hr.libdemo.repository.AutorRepository;
import com.hr.libdemo.service.exception.ObjectNotFoundException;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repo;

    public List<Autor> findAll(){
        return repo.findAll();
    }

    public Autor findById(String id) {
        Autor autor = repo.findById(id).orElse(null);
        if (autor == null) {
            throw new ObjectNotFoundException("Autor não encontrado");
        }
        return autor;
    }

    public Autor insert(Autor obj){
        return repo.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public String update(Autor obj) {
        Autor newObj = findById(obj.getId());
        updateData(newObj, obj);
        repo.save(newObj);

        return "O Autor " + obj.getNome() + " foi alterado com sucesso";
    }

    private void updateData(Autor newObj, Autor obj) {
        newObj.setNome(obj.getNome());
        newObj.setSobrenome(obj.getSobrenome());
    }

    

}
