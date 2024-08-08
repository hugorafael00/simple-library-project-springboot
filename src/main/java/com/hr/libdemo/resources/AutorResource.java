package com.hr.libdemo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hr.libdemo.domain.Autor;
import com.hr.libdemo.domain.Livro;
import com.hr.libdemo.service.AutorService;
import com.hr.libdemo.service.LivroService;

@RestController
@RequestMapping(value = "/autores")
public class AutorResource {

    @Autowired
    private AutorService service;

    @Autowired
    private LivroService livroService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Autor>> findAll() {
        List<Autor> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Autor> findById(@PathVariable String id) {
        Autor obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Autor obj1) {
        Autor obj = service.insert(obj1);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody Autor obj, @PathVariable String id) {
        return ResponseEntity.ok().body(service.update(obj));
    }

    @RequestMapping(value = "/{id}/livros", method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> findBooks(@PathVariable String id) {
        Autor obj = service.findById(id);
        List<String> list = obj.getLivrosId();
        List<Livro> list2 = list.stream().map(x -> livroService.findById(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(List.copyOf(list2));
    }
}
