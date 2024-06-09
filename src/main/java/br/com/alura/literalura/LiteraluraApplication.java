package br.com.alura.literalura;


import br.com.alura.literalura.controller.HttpClientController;
import br.com.alura.literalura.service.HttpClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class LiteraluraApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(LiteraluraApplication.class, args);

        HttpClientService clientService = new HttpClientService();
        clientService.sendGetRequest("https://gutendex.com/books/?");

    }
}


