package br.com.alura.literalura.service;

import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

//    public LivroDTO getLivroById(Long id) {
//        Livro livro = livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Livro not found"));
//        return new LivroDTO(livro);
//    }
}




