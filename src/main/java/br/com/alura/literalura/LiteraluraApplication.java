package br.com.alura.literalura;


import br.com.alura.literalura.model.repository.AutorRepository;
import br.com.alura.literalura.model.repository.LivroRepository;
import br.com.alura.literalura.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    @Autowired
    private LivroRepository repositorio;

    @Autowired
    private AutorRepository autorRepositorio;

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repositorio, autorRepositorio);
        principal.exibeMenu();
    }

}


