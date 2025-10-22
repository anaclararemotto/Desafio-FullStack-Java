package br.com.juridico.totvs.fullstack.Backend.controller;

import br.com.juridico.totvs.fullstack.Backend.service.PontoTuristicoService;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PaisCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PaisDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoRequest;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<PontoTuristicoResponse> criarPonto(@Valid @RequestBody PontoTuristicoRequest request) {
        PontoTuristicoResponse response = pontoTuristicoService.criar(request);
        //retone 201
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //endpoint de buscar todos GET /api/pontos-turisticos
    @GetMapping
    public ResponseEntity<List<PontoTuristicoResponse>> buscarTodos() {
        List<PontoTuristicoResponse> lista = pontoTuristicoService.buscarTodos();
        return ResponseEntity.ok(lista);
    }

    //endpoint buscar; por id GET /api/pontos-turisticos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PontoTuristicoResponse> buscarPorId(@PathVariable Long id) {
        PontoTuristicoResponse ponto = pontoTuristicoService.buscarPorId(id);
        //o service ja trata o erro 400
        //retorna 200
        return ResponseEntity.ok(ponto);
    }

    //endpoint atualizar PUT /api/pontos-turisticos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PontoTuristicoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PontoTuristicoRequest request) {
        //garantindo que o id de atualização nao seja passado para o service
        request.setId(id);
        PontoTuristicoResponse atualizado = pontoTuristicoService.atualizar(id, request);
        return ResponseEntity.ok(atualizado);
    }

    //endpoint deletar DELETE /api/pontos-turisticos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pontoTuristicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/teste-dto")
    public ResponseEntity<String> testeDto(@RequestBody Map<String, Object> body) {
        System.out.println("Recebeu: " + body);
        return ResponseEntity.ok("DTO recebido");
    }
}
