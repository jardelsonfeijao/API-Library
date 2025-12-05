package com.example.libraryapi.web.controller;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.service.AutorService;
import com.example.libraryapi.web.dto.AutorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

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

        // http://localhost:8080/api/autores/ij9j9j-0909j0j-0j09jj0-j0j09jj
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntidade.getId())
                .toUri();

//        return new ResponseEntity("Autor salvo com sucesso! " + autorDTO + "", HttpStatus.CREATED);
        return ResponseEntity.created(location).build();
    }


    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {
          var idAutor = UUID.fromString(id);
          Optional<Autor> autorOptional = autorService.obterPorId(idAutor);
          if (autorOptional.isPresent()) {
              Autor autor = autorOptional.get();
              AutorDTO autorDTO = new AutorDTO(
                      autor.getId(),
                      autor.getNome(),
                      autor.getDataNascimento(),
                      autor.getNacionalidade());
              return ResponseEntity.ok(autorDTO);
          } else {
              return ResponseEntity.notFound().build();
          }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        autorService.deletarAutor(autorOptional.get());
        return ResponseEntity.noContent().build();
    }
}
