package br.com.juridico.totvs.fullstack.Backend.service.dto;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentario;
import java.time.format.DateTimeFormatter;

public class ComentarioResponse {
    private Long id;
    private String autor;
    private String textoComentario;
    //retornar a data ja formatada no front
    private String dataCriacaoFormatada;
    private Long pontoTuristicoId;

    public ComentarioResponse(Comentario entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        this.id = entity.getId();
        this.autor = entity.getAutor();
        this.textoComentario = entity.getTextoComentario();
        //MAPEAMENTO E FORMATACAO DA DATA
        this.dataCriacaoFormatada = entity.getDataCriacao() != null ? entity.getDataCriacao().format(formatter) : "Data indisponivel";
        //mapeamento do relacionamento
        if (entity.getPontoTuristico() != null) {
            this.pontoTuristicoId = entity.getPontoTuristico().getId();
        }
    }

    public ComentarioResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    public String getDataCriacaoFormatada() {
        return dataCriacaoFormatada;
    }

    public void setDataCriacaoFormatada(String dataCriacaoFormatada) {
        this.dataCriacaoFormatada = dataCriacaoFormatada;
    }

    public Long getPontoTuristicoId() {
        return pontoTuristicoId;
    }

    public void setPontoTuristicoId(Long pontoTuristicoId) {
        this.pontoTuristicoId = pontoTuristicoId;
    }
}