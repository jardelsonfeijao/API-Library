package com.example.libraryapi.repository;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroReposirory livroReposirory;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setDataNascimento(LocalDate.of(2000, 01, 04));
        autor.setNacionalidade("Brasileira");

        var autorSalvo = autorRepository.save(autor);

        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("ecacf839-917c-4cc2-a0d3-c0f6c886f49f");
        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.print("Dados do autor: ");
            System.out.println(possivelAutor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(2004, 12, 01));
            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest() {
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);

    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("c3abc166-372c-4099-9a19-a053ba783121");
        autorRepository.deleteById(id);
        System.out.println("Autor com id: " + id + " Deletado!");
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("62ed6cc2-6efe-4cd4-9dd6-239c1f38e3ed");
        var maria = autorRepository.findById(id).get();
        autorRepository.delete(maria);
    }

    @Test
    void salvarAutorComLivros() {
        Autor autor = new Autor();
        autor.setNome("Test");
        autor.setDataNascimento(LocalDate.of(2000, 01, 04));
        autor.setNacionalidade("Brasileira");

        Livro livro = new Livro();
        livro.setIsbn("90887-54865");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("O roubo");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("90887-78799");
        livro2.setPreco(BigDecimal.valueOf(100));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("O museu de narnia");
        livro2.setDataPublicacao(LocalDate.of(1980, 1, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);
        livroReposirory.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor() {
        var id = UUID.fromString("9abca618-e371-4383-a564-d686f12ee365");
        var autor = autorRepository.findById(id).get();

        // buscar livros do autor
        List<Livro> livrosList = livroReposirory.findByAutor(autor);
        autor.setLivros(livrosList);

        autor.getLivros().forEach(System.out::println);
    }
}
