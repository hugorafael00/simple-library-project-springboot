package com.hr.libdemo.resources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hr.libdemo.domain.Livro;
import com.hr.libdemo.resources.util.URL;
import com.hr.libdemo.service.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

    @Autowired
    private LivroService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Livro> findById(@PathVariable String id) {
        Livro obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> findAll() {
        List<Livro> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/pesquisarTitulo", method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> findByTitulo(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Livro> list = service.findByTituloContaining(text);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/fullSearch", method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "anoMin", defaultValue = "0") Integer anoMin,
            @RequestParam(value = "anoMax", defaultValue = "9999") Integer anoMax) {
        text = URL.decodeParam(text);
        List<Livro> list = service.fullSearch(text, anoMin, anoMax);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody Map<String, String> obj) {
        String response = service.add(obj);
        return ResponseEntity.ok().body(response);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().body("Livro removido com sucesso");
    }
}
