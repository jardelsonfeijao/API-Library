package com.example.libraryapi.repository;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroReposiroryTest {

    @Autowired
    LivroReposirory livroReposirory;

    @Autowired
    AutorRepository autorRepository;


    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository.findById(UUID.fromString("cf66d359-ace1-4c32-a51d-22da5e50c13c")).orElse(null);
        livro.setAutor(autor);

        livro.setAutor(new Autor());

        livroReposirory.save(livro);
    }

    @Test
    void salvarLivroEAutorTest() {

        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Jota");
        autor.setDataNascimento(LocalDate.of(2001, 4, 12));
        autor.setNacionalidade("Brasileiro");

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroReposirory.save(livro);
    }

    @Test
    void salvarCascadeTest() {

        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setDataNascimento(LocalDate.of(2001, 4, 4));
        autor.setNacionalidade("Brasileiro");

        livro.setAutor(autor);

        livroReposirory.save(livro);
    }

    @Test
    void atualizarAutor() {
        UUID idLivro = UUID.fromString("fc17d878-730a-420f-86f0-33073b6fc510");
        var livroParaAtualizar = livroReposirory.findById(idLivro).orElse(null);

        UUID idAutor = UUID.fromString("cf66d359-ace1-4c32-a51d-22da5e50c13c");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        livroReposirory.save(livroParaAtualizar);

    }

    @Test
    void deletar() {
        UUID idLivro = UUID.fromString("fc17d878-730a-420f-86f0-33073b6fc510");
        livroReposirory.deleteById(idLivro);
    }

    @Test
    @Transactional
    void buscarLivroTest() {
        UUID idLivro = UUID.fromString("3f951786-e521-44a0-9578-9ff9b6801bee");
        Livro livro = livroReposirory.findById(idLivro).orElse(null);
        System.out.print("Livro: ");
        System.out.println(livro.getTitulo());

//        System.out.print("Autor: ");
//        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisaPorTituloTest() {
        List<Livro> lista = livroReposirory.findByTitulo("UFO");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorIsbnTest() {
        List<Livro> lista = livroReposirory.findByIsbn("90887-84874");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorTituloEPrecoTest() {
        var preco = BigDecimal.valueOf(100.00);

        List<Livro> list = livroReposirory.findByTituloAndPreco("UFO", preco);
        list.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQLTest() {
        var resultadoPesquisa = livroReposirory.listarTodosOrdenadoPorTituloAndPreco();
        resultadoPesquisa.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivrosTest() {
        var resultadoPesquisa = livroReposirory.listarAutoresDosLivros();
        resultadoPesquisa.forEach(System.out::println);
    }

    @Test
    void listarTitulosNaoRepetidosDosLivrosTest() {
        var resultadoPesquisa = livroReposirory.listarNomesDiferentesLivros();
        resultadoPesquisa.forEach(System.out::println);
    }

    @Test
    void listarGeneroDeLivrosAutoresBrasileirosTest() {
        var resultadoPesquisa = livroReposirory.listarGeneroAutoresBrasileiros();
        resultadoPesquisa.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest() {
        var resultadoPesquisa = livroReposirory.findByGenero(GeneroLivro.MISTERIO, "data_publicacao");
        resultadoPesquisa.forEach(System.out::println);
    }


    @Test
    void listarPorGeneroPositionlParamTest() {
        var resultadoPesquisa = livroReposirory.findByGeneroPositionalParameters(GeneroLivro.MISTERIO, "data_publicacao");
        resultadoPesquisa.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest() {
        livroReposirory.deletarByGenero(GeneroLivro.FICCAO);
    }

    @Test
    void updateDataPublicacaoTest() {
        livroReposirory.updateDataPublicacao(LocalDate.of(2000, 1, 19));
    }
}