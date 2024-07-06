package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.HttpClientService;
import br.com.alura.literalura.service.MapeandoDados;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    Scanner leitura = new Scanner(System.in);
    private MapeandoDados conversor = new MapeandoDados();
    private HttpClientService clientService = new HttpClientService();
    private final String URL = "https://gutendex.com/books/?search=";

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepositorio) {
        this.livroRepository = livroRepository;
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
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
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
                    break;
                case 0:
                    System.out.println("saindo.....");
                    opcao = 0;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    String nomeLivro;
    private void buscaLivroPeloTitulo() throws IOException, InterruptedException {
        System.out.println("Insira o nome do livro que você deseja procurar ");
        nomeLivro = leitura.nextLine();
        var dados = clientService.sendGetRequest(URL + nomeLivro.replace(" ", "%20"));
        salvarNoDB(dados);
    }

    private void salvarNoDB(String dados) {
        try {
            Livro livro = new Livro(conversor.pegarDados(dados, DadosLivro.class));
            Autor autor = new Autor(conversor.pegarDados(dados, DadosAutor.class));
            Autor autorDb = null;
            Livro livroDb = null;
            if (!autorRepository.existsByNome(autor.getNome())) {
                autorRepository.save(autor);
                autorDb = autor;
            } else {
                autorDb = autorRepository.findByNome(autor.getNome());
            }
            if (!livroRepository.existsByNome(livro.getNome())) {
                livro.setAutor(autorDb);
                livroRepository.save(livro);
                livroDb = livro;
            } else {
                livroDb = livroRepository.findByNome(livro.getNome());
            }
            System.out.println(livroDb);
        } catch (NullPointerException e) {
            System.out.println("\n\n=============   L i v r o   n ã o   e n c o n t r a d o     ================\n\n");
        }
    }

    private void listaLivroRegistrado() {
        var bucasDB = livroRepository.findAll();
        if (!bucasDB.isEmpty()) {
            System.out.println("\nLivros cadastrados no Banco de Dados: ");
            bucasDB.forEach(System.out::println);
        } else {
            System.out.println("\nNenhum livro encontrado no Banco de Dados!");
        }
    }

    private void listaAutoresRegistrados() {
        var buscaDb = autorRepository.findAll();
        if (!buscaDb.isEmpty()) {
            System.out.println("\nAutores cadastrados no Banco de Dados:");
            buscaDb.forEach(System.out::println);
        } else {
            System.out.println("\nNenhum autor encontrado no Banco de Dados!");
        }
    }

    private void listaAutoresVivos() {
        System.out.println("\nQual ano deseja pesquisar?");
        var anoSelecionado = leitura.nextInt();
        leitura.nextLine();
        var buscaAutoresNoDb = autorRepository.buscarPorAnoDeFalecimento(anoSelecionado);
        if(!buscaAutoresNoDb.isEmpty()){
            System.out.println("\n\nAtores vivos no ano de: " + anoSelecionado);
            buscaAutoresNoDb.forEach(System.out::println);
        }else {
            System.out.println("\nNenhum autor encontrado para esta data!");
        }
    }

    private void listaLivrosPorIdioma() {
        var idiomasCadastrados = livroRepository.buscaIdiomaNoDB();
        System.out.println("\nIdiomas cadastrados no Banco de Dados:");
        idiomasCadastrados.forEach(System.out::println);
        System.out.println("\nSelecione um dos idiomas cadastrados no Banco de Dados:\n");
        var idiomaSelecionado = leitura.nextLine();
        livroRepository.buscarPorIdioma(idiomaSelecionado).forEach(System.out::println);
    }

}
