package com.example.libraryapi.service;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import com.example.libraryapi.repository.AutorRepository;
import com.example.libraryapi.repository.LivroReposirory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private LivroReposirory livroReposirory;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public void salvarLivroComFoto() {
        // salvar o livro
        // livroRepository.save(livro);

        // pega o id do livro = livro.getId();
        // var id = livro.getId();

        // salvar foto de um livro -> bucket na nuvem
        // bucketService.salvar(livro.getFoto(), id + ".png");

        // atualizar o nome arquivo que foi salvo
        // livro.setNomeArquivoFoto(id + ".png ");
    }

    @Transactional
    public void atualizacaoSemAtualizar() {
        var livro = livroReposirory
                .findById(UUID.fromString("adb51617-7456-4bf6-8ce3-4fd7ff37ef57"))
                .orElse(null);

        if (livro != null) {
            livro.setDataPublicacao(LocalDate.of(2024, 6, 29));
        }
    }

    @Transactional
    public void executar() {
        // salva o autor
        Autor autor = new Autor();
        autor.setNome("Jota04");
        autor.setDataNascimento(LocalDate.of(2001, 4, 12));
        autor.setNacionalidade("Brasileiro");

        autorRepository.save(autor);


        // salva o livro
        Livro livro = new Livro();
        livro.setIsbn("90832-84874");
        livro.setPreco(BigDecimal.valueOf(950));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("O trouxa");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));
        livro.setAutor(autor);

        livroReposirory.save(livro);


//        if (autor.getNome().equals("Jota04")) {
//            throw new RuntimeException("Rollback");
//        }
    }

}
