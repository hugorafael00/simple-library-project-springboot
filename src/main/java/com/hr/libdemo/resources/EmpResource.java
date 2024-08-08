package com.hr.libdemo.resources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hr.libdemo.domain.Emprestimo;
import com.hr.libdemo.service.EmpService;

@RestController
@RequestMapping(value = "/emprestimo")
public class EmpResource {

    @Autowired
    private EmpService service;


    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public ResponseEntity<List<Emprestimo>> findAll() {
        List<Emprestimo> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Emprestimo> criarEmp(@RequestBody Map<String, String> obj) {
        String UsuarioId = obj.get("UsuarioId");
        String LivroId = obj.get("LivroId");
        Emprestimo response = service.criarEmp(UsuarioId, LivroId);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/devolver", method = RequestMethod.POST)
    public ResponseEntity<String> devolverEmp(@RequestBody String obj) {
        String id = obj.get("id");
        String response = service.devolverEmp(id);

        return ResponseEntity.ok().body(response);
    }

    

}
