package com.example.libraryapi.web.controller;

import com.example.libraryapi.service.AutorService;
import com.example.libraryapi.web.dto.AutorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("api/autores")
// http://localhost:8080/api/autores
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autorDTO) {
        var autorEntidade = autorDTO.mapearParaAutor();
        autorService.salvarAutor(autorEntidade);

        // http://localhost:8080/api/autores/ij9j9j0909j0j0j09j0j0j0j09jj0j0
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntidade.getId())
                .toUri();

//        return new ResponseEntity("Autor salvo com sucesso! " + autorDTO + "", HttpStatus.CREATED);
        return ResponseEntity.created(location).build();
    }
}
