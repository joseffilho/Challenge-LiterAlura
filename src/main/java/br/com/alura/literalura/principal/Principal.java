package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Biblioteca;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.model.repository.AutorRepository;
import br.com.alura.literalura.model.repository.LivroRepository;
import br.com.alura.literalura.service.HttpClientService;
import br.com.alura.literalura.service.MapeandoDados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    Scanner leitura = new Scanner(System.in);

    private List<Livro> livroList = new ArrayList<>();
    private List<Autor> autorList = new ArrayList<>();

    public Principal(LivroRepository repositorio, AutorRepository autorRepositorio) {
        this.repositorio = repositorio;
        this.autorRepository = autorRepositorio;
    }



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
                    buscaLivroPeloTitulo();
                    break;
                case 2:
                    listaLivroRegistrado();
                    break;
                case 3:
                    listaAutoresRegistrados();
                    break;
                case 4:
                    listaAutoresVivos();
                    break;
                case 5:
                    listaLivrosPorIdioma();
                    //listar livros em um determinado idioma
                    break;
                case 0:
                    System.out.println("saindo.....");
                    opcao = 0;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void buscaLivroPeloTitulo() throws IOException, InterruptedException {
        System.out.println("Insira o nome do livro que você deseja procurar ");
        nomeLivro = leitura.nextLine().replace(" ", "%20");
        exibeLacoFor();
    }
    private void listaLivroRegistrado() {
        livroList.forEach(System.out::println);
    }
    private void listaAutoresRegistrados() {
        System.out.println("Autores registrados: " + autorList);
    }
    private void listaAutoresVivos() {
        System.out.println("Digite o ano desejado: ");
        int ano = leitura.nextInt();
        if (ano > autorMorreu) {
            System.out.println("Este autor já morreu! ");
        } else if (ano < autorNasceu) {
            System.out.println("Este autor ainda não nasceu! ");
        } else {
            System.out.println("Este autor ainda está vivo! ");
        }
    }
    private void listaLivrosPorIdioma() {
    }



    private LivroRepository repositorio;
    private AutorRepository autorRepository;


    String livroTitle;
    String livroIdioma;
    int livroDownload;

    String autorNome;
    Integer autorMorreu = 0;
    Integer autorNasceu = 0;

    String nomeLivro;
    public void exibeLacoFor() throws IOException, InterruptedException {
        HttpClientService clientService = new HttpClientService();
        var json = clientService.sendGetRequest("https://gutendex.com/books/?search=" + nomeLivro);

        MapeandoDados mapeando = new MapeandoDados();
        Biblioteca dados = mapeando.pegarDados(json, Biblioteca.class);

        for (Livro livro : dados.getResults()) {
            livroList.add(livro);
            livroTitle = livro.getTitle();

            repositorio.save(livro);

            System.out.println("------- LIVRO -------");
            System.out.println("Title: " + livro.getTitle());
            for (Autor autor : livro.getAuthors()) {
                autorList.add(autor);
                autorNome = autor.getName();

                autorRepository.save(autor);

                System.out.println("Author Name: " + autor.getName());

                autorNasceu = autor.getBirth_year();
                autorMorreu = autor.getDeath_year();
            }
            for (String idioma : livro.getLanguages())
                livroIdioma = idioma;
            System.out.println("Language: " + livro.getLanguages());
            livroDownload = livro.getDownload_count();
            System.out.println("Download_count: " + livro.getDownload_count());
        }


    }
//    private Resultado salvaDados() {
//        System.out.println("------- LIVRO -------");
//        System.out.println("Title: " + livroTitle);
//        System.out.println("Author Name: " + autorNome);
//        System.out.println("Language: " + livroIdioma);
//        System.out.println("Download_count: " + livroDownload);
//        return null;
//    }




}
