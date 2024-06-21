package br.com.alura.literalura;


import br.com.alura.literalura.principal.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
//   @Autowired
//   private ClasseRepository repositorio;

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(LiteraluraApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        Menu menu = new Menu();
        menu.exibeMenu();
    }


}


