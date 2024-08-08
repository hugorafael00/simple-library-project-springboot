package com.hr.libdemo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String idAcesso;
    private String nome;
    private String codigo;

    private List<Livro> booklist = new ArrayList<>();

    public Usuario() {}

    public Usuario(String idAcesso, String nome, String codigo) {
        this.idAcesso = idAcesso;
        this.nome = nome;
        this.codigo = codigo;
    }

    public List<Livro> getBooklist() {
        return booklist;
    }

    public void setBooklist(List<Livro> booklist) {
        this.booklist = booklist;
    }

    public void addBooklist(Livro livro) {
        this.booklist.add(livro);
    }

    public void removeBooklist(Livro livro) {
        this.booklist.remove(livro);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(String idAcesso) {
        this.idAcesso = idAcesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idAcesso == null) ? 0 : idAcesso.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (idAcesso == null) {
            if (other.idAcesso != null)
                return false;
        } else if (!idAcesso.equals(other.idAcesso))
            return false;
        return true;
    }

}
