package br.com.juridico.totvs.fullstack.Backend.repository;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ComentarioRepository  extends JpaRepository<Comentario, Long>{

    /**
     * Buscar todos os comentarios que pertencem a um ponto turistico
     *
     * @param id do ponto turistico
     * @return lista de comentarios ordenadoe pela data de criação
     */
    List<Comentario> findByPontoTuristicoIdOrderBydataCriacaoDesc(Long pontoTuristicoId);


}
