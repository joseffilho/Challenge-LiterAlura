package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Biblioteca;
import br.com.alura.literalura.model.Resultado;
import br.com.alura.literalura.service.HttpClientService;
import br.com.alura.literalura.service.MapeandoDados;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    Scanner leitura = new Scanner(System.in);


    public void exibeMenu() throws IOException, InterruptedException {
        String text = """
                ===============
               \s
                ---------------
                Escolha o número de sua opção:
                1- buscar livro pelo título
                2- listar livros registrados
                3- listar autores registrados
                4- listar autores vivos em um determinado ano
                5- listar livros em um determinado idioma
                0- sair
                               \s
               \s""";

        int opcao = -1;
        while (opcao != 0) {
            System.out.println(text);
            System.out.println("Digite o número desejado: ");
            int numeroDigitado = leitura.nextInt();
            leitura.nextLine();

            switch (numeroDigitado) {
                case 1:
                    System.out.println("Insira o nome do livro que você deseja procurar ");
                    nomeLivro = leitura.nextLine().replace(" ", "%20");
                    exibeLacoFor();
                    break;
                case 2:
                    // Implementação para listar livros registrados
                    break;
                case 3:
                    // Implementação para listar autores registrados
                    break;
                case 4:
                    // Implementação para listar autores vivos em um determinado ano
                    break;
                case 5:
                    // Implementação para listar livros em um determinado idioma
                    break;
                case 0:
                    opcao = 0;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    String nomeLivro;
    public void exibeLacoFor() throws IOException, InterruptedException {
        HttpClientService clientService = new HttpClientService();
        var json = clientService.sendGetRequest("https://gutendex.com/books/?search=" + nomeLivro);

        MapeandoDados mapeando = new MapeandoDados();
        Biblioteca dados = mapeando.pegarDados(json, Biblioteca.class);

        Resultado result;
        Autor resultAutor = null;

        for (Resultado resultado : dados.getResults()) {
            result = resultado;
            System.out.println("------- LIVRO -------");
            System.out.println("Title: " + resultado.getTitle());
            for (Autor autor : resultado.getAuthors()) {
                resultAutor = autor;
                System.out.println("Author Name: " + autor.getName());
            }
            for (String idioma : resultado.getLanguages()) {
                System.out.println("Language: " + idioma);
            }
            System.out.println("Download_count: " + resultado.getDownload_count());

            System.out.println(result);
            System.out.println(resultAutor);
        }

    }
}
