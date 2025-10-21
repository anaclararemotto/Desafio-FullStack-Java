package br.com.juridico.totvs.fullstack.Backend.service;


import br.com.juridico.totvs.fullstack.Backend.domain.Pais;
import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;
import br.com.juridico.totvs.fullstack.Backend.repository.PaisRepository;
import br.com.juridico.totvs.fullstack.Backend.repository.PontoTuristicoRepository;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoRequest;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PontoTuristicoServiceImpl implements PontoTuristicoService {

    private final PontoTuristicoRepository pontoTuristicoRepository;
    private final PaisRepository paisRepository;

    //injeção de dependencia via constructor
    public PontoTuristicoServiceImpl(PontoTuristicoRepository pontoTuristicoRepository, PaisRepository paisRepository) {
        this.pontoTuristicoRepository = pontoTuristicoRepository;
        this.paisRepository = paisRepository;
    }

    @Override
    @Transactional
    public PontoTuristicoResponse criar(PontoTuristicoRequest request) {
        //garantidno que pais existe
        Pais pais = paisRepository.findById(request.getPaisId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pais com ID " + request.getPaisId() + " não encontrado"));
        //MApeando entidade DTO
        PontoTuristico entity = new PontoTuristico(request);
        //realciona ap pais buscado
        entity.setPais(pais);

        //persistencia e retorno
        PontoTuristico salvo = pontoTuristicoRepository.save(entity);

        //mapenaod entidade e response do DTO
        return new PontoTuristicoResponse(salvo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PontoTuristicoResponse> buscarTodos() {
        return pontoTuristicoRepository.findAll().stream().map(PontoTuristicoResponse::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PontoTuristicoResponse buscarPorId(Long id) {
        //busca a entity ou lança uma exceção (404)
        PontoTuristico entity = pontoTuristicoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ponto Turistico com o ID " + id + " nâo encontrado"));

        return new PontoTuristicoResponse(entity);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!pontoTuristicoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ponto Turistico com ID " + id + " não encontrado para a exclusão");
        }
        pontoTuristicoRepository.deleteById(id);
    }
}
