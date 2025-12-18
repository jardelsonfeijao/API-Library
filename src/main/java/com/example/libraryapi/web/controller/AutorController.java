package com.example.libraryapi.web.controller;

import com.example.libraryapi.exceptions.OperacaoNaoPermitidaException;
import com.example.libraryapi.exceptions.RegistroDuplicadoException;
import com.example.libraryapi.model.Autor;
import com.example.libraryapi.service.AutorService;
import com.example.libraryapi.web.dto.AutorDTO;
import com.example.libraryapi.web.dto.ErroResposta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/autores")
// http://localhost:8080/api/autores
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid AutorDTO autorDTO) {
        try {
            var autorEntidade = autorDTO.mapearParaAutor();
            autorService.salvarAutor(autorEntidade);

            // http://localhost:8080/api/autores/ij9j9j-0909j0j-0j09jj0-j0j09jj
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(autorEntidade.getId())
                    .toUri();

    //        return new ResponseEntity("Autor salvo com sucesso! " + autorDTO + "", HttpStatus.CREATED);

            ErroResposta erroResposta = ErroResposta.erroRespostaConflito("Autor já cadastrado!");
    //        return ResponseEntity.status(erroResposta.status()).build(erroResposta);
            return ResponseEntity.created(location).build();
        } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.erroRespostaConflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {

          var idAutor = UUID.fromString(id);
          Optional<Autor> autorOptional = autorService.obterAutorPorId(idAutor);

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
    public ResponseEntity<Object> deletar(@PathVariable("id") String id) {
        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = autorService.obterAutorPorId(idAutor);

            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            autorService.deletarAutor(autorOptional.get());
            return ResponseEntity.noContent().build();

        } catch (OperacaoNaoPermitidaException e) {
            var erroResposta = ErroResposta.erroRespostaPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {

        List<Autor> resultado = autorService.pesquisaByExample(nome, nacionalidade);
        List<AutorDTO> lista = resultado
                .stream()
                .map(autor -> new AutorDTO(
                        autor.getId(),
                        autor.getNome(),
                        autor.getDataNascimento(),
                        autor.getNacionalidade())
                ).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(
            @PathVariable("id") String id,
            @RequestBody AutorDTO autorDTO) {

        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = autorService.obterAutorPorId(idAutor);

            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var autor = autorOptional.get();
            autor.setNome(autorDTO.nome());
            autor.setNacionalidade(autorDTO.nacionalidade());
            autor.setDataNascimento(autorDTO.dataNascimento());

            autorService.atualizarAutor(autor);

            return ResponseEntity.noContent().build();
    } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.erroRespostaConflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

}
