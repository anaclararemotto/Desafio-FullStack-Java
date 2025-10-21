package br.com.juridico.totvs.fullstack.Backend.service.dto;

import javax.validation.constraints.NotBlank;

public class ComentarioRequest {
    //o ID não sera necessario pois a criação sera sempre uma nova entrada
    @NotBlank(message = "O nome do autor é obrigatório")
    private String autor;

    @NotBlank(message = "O texto do comentario é obrigatório")
    private String textoComentario;

    @NotBlank(message = "O ID do ponto turistico é obrigatório")
    private Long pontoTuristicoId;

    public ComentarioRequest() {}

    public ComentarioRequest(String autor, String textoComentario, Long pontoTuristicoId) {
        this.autor = autor;
        this.textoComentario = textoComentario;
        this.pontoTuristicoId = pontoTuristicoId;
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


    public Long getPontoTuristicoId() {
        return pontoTuristicoId;
    }

    public void setPontoTuristicoId(Long pontoTuristicoId) {
        this.pontoTuristicoId = pontoTuristicoId;
    }


}