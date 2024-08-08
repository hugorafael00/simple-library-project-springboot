package com.hr.libdemo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.hr.libdemo.domain.Autor;
import com.hr.libdemo.domain.Livro;
import com.hr.libdemo.domain.Usuario;
import com.hr.libdemo.repository.AutorRepository;
import com.hr.libdemo.repository.LivroRepository;
import com.hr.libdemo.repository.UsuarioRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository userRepo;


    @Override
    public void run(String... args) throws Exception {
        // Instantiation of the database
        // autorRepository.deleteAll();
        // livroRepository.deleteAll();
        // userRepo.deleteAll();

        Usuario user1 = new Usuario(null, "João da Silva", "001");

        userRepo.save(user1);

        Autor crsete = new Autor(null, "Cristiano", "Ronaldo");
        Autor lionel = new Autor(null, "Lionel", "Messi");
        Autor eduardo = new Autor(null, "Eduardo", "Politano");

        autorRepository.saveAll(Arrays.asList(crsete, lionel, eduardo));

        Livro gajo = new Livro(null, "Como se tornar o maior jogador da historia", crsete, "Ilha da madeira", 2010);
        Livro et = new Livro(null, "Que mira bobo", lionel , "ET", 2022);

        livroRepository.saveAll(Arrays.asList(gajo, et));

        crsete.getLivrosId().addAll(Arrays.asList(gajo.getId()));
        autorRepository.save(crsete);
    }

}
