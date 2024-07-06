package br.com.alura.literalura.service;

import br.com.alura.literalura.model.DadosAutor;
import br.com.alura.literalura.model.DadosLivro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapeandoDados implements IMapeandoDados {
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T pegarDados(String json, Class<T> classe) {
        T resultado = null;
        try {
            if (classe == DadosLivro.class) {
                JsonNode node = mapper.readTree(json);
                var s = node.get("results").get(0);
                resultado = mapper.treeToValue(s, classe);
            } else if (classe == DadosAutor.class) {
                JsonNode node = mapper.readTree(json);
                var s = node.get("results").get(0).get("authors").get(0);
                resultado = mapper.treeToValue(s, classe);
            } else {
                resultado = mapper.readValue(json, classe);
            }
        }catch(JsonProcessingException e){
                throw new RuntimeException(e);
            }
            return resultado;
        }
    }
