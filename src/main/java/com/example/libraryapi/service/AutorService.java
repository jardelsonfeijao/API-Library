package com.example.libraryapi.service;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository1) {
        this.autorRepository = autorRepository1;
    }

    public Autor salvarAutor(Autor autor) {
        return autorRepository.save(autor);
    }


}
