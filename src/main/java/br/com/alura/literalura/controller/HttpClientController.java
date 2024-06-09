package br.com.alura.literalura.controller;

import br.com.alura.literalura.service.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class HttpClientController {

    @Autowired
    private HttpClientService httpClientService;

    @GetMapping("/get")
    public String get(@RequestParam String url) throws IOException, InterruptedException {
        return httpClientService.sendGetRequest(url);
    }
}
