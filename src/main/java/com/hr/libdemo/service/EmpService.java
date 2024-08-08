package com.hr.libdemo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.libdemo.domain.Emprestimo;
import com.hr.libdemo.domain.Livro;
import com.hr.libdemo.domain.Usuario;
import com.hr.libdemo.repository.EmpRepository;
import com.hr.libdemo.repository.LivroRepository;
import com.hr.libdemo.repository.UsuarioRepository;
import com.hr.libdemo.service.exception.ObjectNotFoundException;

@Service
public class EmpService {

    @Autowired
    private EmpRepository EmpRepo;

    @Autowired
    private UsuarioRepository UsuarioRepo;

    @Autowired
    private LivroRepository LivroRepo;

    @Autowired
    private LivroService LivroService;

    int upperbound = 25;

    public List<Emprestimo> findAll() {
        return EmpRepo.findAll();
    }

    public Emprestimo criarEmp(String UsuarioId, String LivroId) {
        int x = (int) (Math.random() * upperbound);

        Usuario usuario = UsuarioRepo.findById(UsuarioId).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
        Livro livro = LivroRepo.findById(LivroId).orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado"));

        if (livro.isDisponivel() == false) {
            throw new ObjectNotFoundException("Livro indisponível");
        }

        String dataEmprestimo = LocalDate.now().toString();
        Emprestimo obj = new Emprestimo(Integer.toString(x), usuario, livro, dataEmprestimo, null);

        livro.setDisponivel(false);
        LivroRepo.save(livro);

        EmpRepo.insert(obj);

        usuario.addBooklist(livro);
        UsuarioRepo.save(usuario);

        return obj;
    }

    public String devolverEmp(String id) {
        Emprestimo emp = EmpRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Emprestimo não encontrado"));

        String dataDevolucao = LocalDate.now().toString();
        emp.setDataDevolucao(dataDevolucao);

        Livro livro = emp.getLivro();
        livro.setDisponivel(true);
        LivroService.update(livro);

        EmpRepo.save(emp);

        Usuario usuario = emp.getUsuario();
        usuario.removeBooklist(livro);
        UsuarioRepo.save(usuario);

        return "Livro devolvido";
    }

}
