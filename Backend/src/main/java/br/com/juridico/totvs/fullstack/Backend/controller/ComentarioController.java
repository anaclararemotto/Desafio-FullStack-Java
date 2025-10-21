package br.com.juridico.totvs.fullstack.Backend.controller;]

import br.com.juridico.totvs.fullstack.Backend.service.ComentarioService;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioRequest;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//aessando comentarios araves do ponto turisticos
@RequestMapping("/api/pontos-turisticos/{pontoTuristicoId}/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    //endpoint criar comentario POST /api/pontos-turisticos/{pontoTuristicoId}/comentarios
    @PostMapping
    public ResponseEntity<ComentarioResponse> criar(@PathVariable Long pontoTuristicoId, @valid @RequestBody ComentarioRequest request) {
        //Garante que o id d URL seja o id usado para criar o relacionamento
        request.setPontoTuristicoId(pontoTuristicoId);

        ComentarioResponse novoComentario = comentarioService.criar(request);
        //Retorna 201
        return new ResponseEntity<>(novoComentario, HttpStatus.CREATED);
    }

    //endpoint bsucar comentario GET /api/pontos-turisticos/{pontoTuristicoId}/comentarios
    @GetMapping
    public ResponseEntity<List<ComentarioResponse>> buscarTodosPorPontoTuristico(@PathVariable Long pontoTuristicoId){
        List<ComentarioResponse> lista = comentarioService.buscarTodosPorPontoTuristico(pontoTuristicoId);
        //Retorna 200
        return ResponseEntity.ok(lista);
    }

    //endpoint deletar comentario DELETE /api/pontos-turisticos/{pontoTuristicoId}/comentarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        comentarioService.deletar(id);
        //retorna 204
        return ResponseEntity.noContent().build();
    }



}
