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

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
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
        List<PontoTuristico> entidades = pontoTuristicoRepository.findAll();
        entidades.forEach(p -> p.getPais().getNome());
        return entidades.stream()
                .map(PontoTuristicoResponse::new)
                .collect(Collectors.toList());
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

    @Override
    @Transactional
    public PontoTuristicoResponse atualizar(Long id, @Valid PontoTuristicoRequest request) {
        //buscar o ponto existente ou lança erro
        PontoTuristico pontoExistente = pontoTuristicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ponto turístico não encontrado com ID " + id));

        //atualiza os campos
        pontoExistente.setNome(request.getNome());
        pontoExistente.setCidade(request.getCidade());
        pontoExistente.setResumo(request.getResumo());
        pontoExistente.setMelhorEstacao(request.getMelhorEstacao());

        //buisca o pais e associa
        Pais pais = paisRepository.findById(request.getPaisId())
                .orElseThrow(() -> new EntityNotFoundException("País não encontrado com ID " + request.getPaisId()));
        pontoExistente.setPais(pais);

        //salva a atualização
        pontoTuristicoRepository.save(pontoExistente);

        // retorna o DTO de reposta
        return new PontoTuristicoResponse(pontoExistente);
    }


}
