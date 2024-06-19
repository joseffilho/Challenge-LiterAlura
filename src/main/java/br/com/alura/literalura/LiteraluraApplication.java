package br.com.alura.literalura;


import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Biblioteca;
import br.com.alura.literalura.model.Idioma;
import br.com.alura.literalura.model.Resultado;
import br.com.alura.literalura.service.HttpClientService;
import br.com.alura.literalura.service.MapeandoDados;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class LiteraluraApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(LiteraluraApplication.class, args);

        HttpClientService clientService = new HttpClientService();
        var json = clientService.sendGetRequest("https://gutendex.com/books/?");
        System.out.println(json);

        MapeandoDados mapeando = new MapeandoDados();
        Biblioteca dados = mapeando.pegarDados(json, Biblioteca.class);

        for (Resultado resultado : dados.getResults()) {
            System.out.println("Title: " + resultado.getTitle());
            for (Autor autor : resultado.getAuthors()) {
                System.out.println("Author Name: " + autor.getName());
            }
            for (String idioma : resultado.getLanguages()) {
                System.out.println("Language: " + idioma);
            }
            System.out.println("Download_count: " + resultado.getDownload_count());
        }
    }
}


