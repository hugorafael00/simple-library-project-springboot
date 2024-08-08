package com.hr.libdemo.resources;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hr.libdemo.domain.Usuario;
import com.hr.libdemo.service.UsuarioService;

@RestController
@RequestMapping(value = "/user")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody Usuario obj) {
        service.add(obj);
        return ResponseEntity.ok().body("Usuário criado com sucesso");
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<String> remove(@RequestBody Map<String, String> obj) {
        String id = obj.get("id");
        return ResponseEntity.ok().body(service.remove(id));
    }

}
