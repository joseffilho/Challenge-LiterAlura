package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Biblioteca {
    @JsonProperty("results")
    private List<Resultado> results;

    public List<Resultado> getResults() {
        return results;
    }

    public void setResults(List<Resultado> results) {
        this.results = results;
    }
}
