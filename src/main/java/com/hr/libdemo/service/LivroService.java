package com.hr.libdemo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.libdemo.domain.Autor;
import com.hr.libdemo.domain.Livro;
import com.hr.libdemo.repository.LivroRepository;
import com.hr.libdemo.service.exception.ObjectNotFoundException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repo;

    @Autowired
    private AutorService AutorService;

    int upperbound = 25;

    public Livro findById(String id) {
        Livro obj = repo.findById(id).orElse(null);
        if (obj == null) {
            throw new ObjectNotFoundException("Livro não encontrado");
        }
        return obj;
    }

    public List<Livro> findAll() {
        return repo.findAll();
    }

    public List<Livro> findByTituloContaining(String text) {
        return repo.searchTitulo(text);
    }

    public List<Livro> fullSearch(String text, Integer anoMin, Integer anoMax) {
        return repo.fullSearch(text, anoMin, anoMax);
    }

    public String add(Map<String, String> obj) {
        int x = (int) (Math.random() * upperbound);

        Autor autor = AutorService.findById(obj.get("autorId"));
        String id = Integer.toString(x);

        if (repo.findById(id).orElse(null) == null) {
            throw new ObjectNotFoundException("Livro ja existente");
        }

        Livro livro = new Livro(id, obj.get("titulo"), autor, obj.get("editora"), Integer.valueOf(obj.get("ano")));

        repo.insert(livro);
        return "O Livro " + obj.get("titulo") + " foi adicionado com sucesso";
    }

    public Livro update(Livro obj) {
        return repo.save(obj);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }
}
