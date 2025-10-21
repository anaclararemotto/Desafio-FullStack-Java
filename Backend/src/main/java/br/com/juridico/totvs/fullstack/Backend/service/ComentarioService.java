package br.com.juridico.totvs.fullstack.Backend.service;

import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioResponse;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioRequest;

import java.util.List;

public interface ComentarioService{

    //Cria um comentario para um pponto turistico especifico
    /*
   @param
   @return
     */
    ComentarioResponse criar(ComentarioRequest request);

    //Busca todos os comentarios do ponto turistico
    /*
   @param
   @return
     */
    List<ComentarioResponse> buscarTodosPorPontoTuristico(Long pontoTuristicoId);

    //Deletar um comentario por id
    /*
   @param
   */
    void deletar(Long id);
}
