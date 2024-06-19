package br.com.alura.literalura.service;

public interface IMapeandoDados {
    <T> T pegarDados(String json, Class<T> classe);
}
