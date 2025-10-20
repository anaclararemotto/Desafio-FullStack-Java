package br.com.juridico.totvs.fullstack.Backend.service;

import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoResponse;

public interface PontoTuristicoService {
    //Cria e atualiza um ponto turistico
    PontoTuristicoResponse criar(PontoTuristicoRequest request);

    //Busca os pontos turisticos
    List<PontoTuristicoResponse> buscarTodos();

    //Busca por ID
    PontoTuristicoResponse buscarPorId(Long id);

    //dELETA POR ID
    void deletar(Long id);
}
