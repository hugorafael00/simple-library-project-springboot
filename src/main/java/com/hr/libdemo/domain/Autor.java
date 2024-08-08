package com.hr.libdemo.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String nome;
    private String sobrenome;

    private List<String> livrosId = new ArrayList<>();

    public Autor() {}

    public Autor(String id, String nome, String sobrenome) {
        super();
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public List<String> getLivrosId() {
        return livrosId;
    }

    public void setLivrosId(List<String> livrosId) {
        this.livrosId = livrosId;
    }

}
