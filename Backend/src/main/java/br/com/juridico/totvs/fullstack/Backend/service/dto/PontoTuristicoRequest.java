package br.com.juridico.totvs.fullstack.Backend.service.dto;

import br.com.juridico.totvs.fullstack.Backend.domain.MelhorEstacao;

import javax.validation.constraints.NotBlank;

public class PontoTuristicoRequest {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "O resumo é obrigatório")
    private String resumo;

    @NotBlank(message = "A melhor estação é obrigatória")
    private MelhorEstacao melhorEstacao;

    //para o service buscar a entity pais
    @NotBlank(message = "O ID do país é obrigatório")
    private Long paisId;

    public PontoTuristicoRequest(){}

    public PontoTuristicoRequest(Long id, String nome, String cidade, String resumo, MelhorEstacao melhorEstacao, Long paisId) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.resumo = resumo;
        this.melhorEstacao = melhorEstacao;
        this.paisId = paisId;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public MelhorEstacao getMelhorEstacao() {
        return melhorEstacao;
    }

    public void setMelhorEstacao(MelhorEstacao melhorEstacao) {
        this.melhorEstacao = melhorEstacao;
    }

    public Long getPaisId() {
        return paisId;
    }

    public void setPaisId(Long paisId) {
        this.paisId = paisId;
    }
}
