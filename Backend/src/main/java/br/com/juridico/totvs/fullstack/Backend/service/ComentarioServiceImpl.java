package br.com.juridico.totvs.fullstack.Backend.service;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentario;
import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;
import br.com.juridico.totvs.fullstack.Backend.repository.ComentarioRepository;
import br.com.juridico.totvs.fullstack.Backend.repository.PontoTuristicoRepository;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioRequest;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceImpl implements ComentarioService{

    private final ComentarioRepository comentarioRepository;
    private final PontoTuristicoRepository pontoTuristicoRepository;

    //injecao de dependencia
    public ComentarioServiceImpl(ComentarioRepository comentarioRepository, PontoTuristicoRepository pontoTuristicoRepository) {
        this.comentarioRepository = comentarioRepository;
        this.pontoTuristicoRepository = pontoTuristicoRepository;
    }

    @Override
    @Transactional
    public ComentarioResponse criar(ComentarioRequest request) {
        //Garante que o ponto existe antes de criar o comentario
        PontoTuristico pontoTuristico = pontoTuristicoRepository.findById(request.getPontoTuristicoId()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ponto turistico vom ID " + request.getPontoTuristicoId() + "não encontrado"));

        //mapeando entidade DTO
        Comentario comentario = new Comentario();
        comentario.setAutor(request.getAutor());
        comentario.setTextoComentario(request.getTextoComentario());
        comentario.setPontoTuristico(pontoTuristico);

        // a data de criaçao eh definida automaticamente

        //persistencia
        Comentario salvo = comentarioRepository.save(comentario);

        //mapeando entidade e response do DTO
        return  new ComentarioResponse(salvo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComentarioResponse> buscarTodosPorPontoTuristico(Long pontoTuristicoId) {
        //busca a lista de comentarios com o mais recente primeiro
        List<Comentario> comentarios = comentarioRepository.findByPontoTuristicoIdOrderByDataCriacaoDesc(pontoTuristicoId);

        //mapeia a lsta de entidade e respobnse do DTO
        return comentarios.stream().map(ComentarioResponse::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentario com ID " + id + " Não encontrado para excluir");
        }
        comentarioRepository.deleteById(id);
    }


}
