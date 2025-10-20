package br.com.juridico.totvs.fullstack.Backend.controller;

import br.com.juridico.totvs.fullstack.Backend.service.PontoTuristicoService;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoRequest;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//Define como um controller rest
@RestController
//define o caminho base dos end points
@RequestMapping("/api/pontos-turisticos")
public class PontoTuristicoController {

    private final PontoTuristicoService pontoTuristicoService;

    //injeção de dependencias do service
    public PontoTuristicoController(PontoTuristicoService pontoTuristicoService) {
        this.pontoTuristicoService = pontoTuristicoService;
    }

    //endpoint de criação POST /api/pontos-turisticos
    @PostMapping
    public ResponseEntity<PontoTuristicoResponse> criar(@Valid @RequestBody PontoTuristicoRequest request){
        //o valid garante que as anotacoes not blank no DTO serao verificadas
        PontoTuristicoResponse novoPonto = pontoTuristicoService.criar(request);
        //retorna 201
        return new ResponseEntity<>(novoPonto, HttpStatus.CREATED);
    }

    //endpoint de buscar todos GET /api/pontos-turisticos
    @GetMapping
    public ResponseEntity<List<PontoTuristicoResponse>> buscarTodos() {
        List<PontoTuristicoResponse> lista =  pontoTuristicoService.buscarTodos();
        return ResponseEntity.ok(lista);
    }

    //endpoint buscar; por id GET /api/pontos-turisticos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PontoTuristicoResponse> buscarPorId(@PathVariable Long id){
        PontoTuristicoResponse ponto = pontoTuristicoService.buscarPorId(id);
        //o service ja trata o erro 400
        //retorna 200
        return ResponseEntity.ok(ponto);
    }

    //endpoint atualizar PUT /api/pontos-turisticos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PontoTuristicoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PontoTuristicoRequest request){
        //garantindo que o id de atualização nao seja passado para o service
        request.setId(id);
        PontoTuristicoResponse atualizado = pontoTuristicoService.criar(request);
        return ResponseEntity.ok(atualizado);
    }

    //endpoint deletar DELETE /api/pontos-turisticos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        pontoTuristicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
