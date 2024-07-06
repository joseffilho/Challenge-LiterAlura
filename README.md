# Biblioteca de Livros

Este projeto é uma aplicação que consome uma API de uma biblioteca de livros. 
Com esta aplicação, os usuários podem pesquisar livros pelo título, visualizar detalhes dos livros encontrados, e salvar essas informações em um banco de dados. 
Além disso, a aplicação permite pesquisar livros salvos, verificar autores e idiomas dos livros.

## Funcionalidades

- **Pesquisa de Livros por Título**: Os usuários podem pesquisar por um livro específico digitando o título.
- Se o livro estiver disponível na biblioteca, as informações serão exibidas e salvas no banco de dados.
- **Consulta de Livros Salvos**: Os usuários podem visualizar todos os livros que foram salvos no banco de dados.
- **Consulta de Autores Salvos**: Os usuários podem listar todos os autores dos livros salvos no banco de dados.
- **Verificação de Autores**: Verifique se um autor ainda está vivo em um determinado ano.
- **Pesquisa de Livros por Idiomas**: Os usuários podem pesquisar livros salvos no banco de dados por idioma.

## Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **Jackson Library** (para manipulação de JSON)
- **PostgreSQL** (para o banco de dados)
- **HTTP Client** (para fazer requisições à API de livros)
