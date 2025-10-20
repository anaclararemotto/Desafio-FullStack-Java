package br.com.juridico.totvs.fullstack.Backend.service.dto;

import br.com.juridico.totvs.fullstack.Backend.domain.MelhorEstacao;
import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;

public class PontoTuristicoResponse{

    private Long id;
    private String nome;
    private String cidade;
    private String resumo;
    private MelhorEstacao melhorEstacao;
    private Long paisId;
    private String paisNome;

    //Construção do DRO de resposta a partir de uma entity JPA
    public PontoTuristicoResponse(PontoTuristico entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cidade = entity.getCidade();
        this.resumo = entity.getResumo();
        this.melhorEstacao = entity.getMelhorEstacao();

        //mapeando o relacionamento
        //o acesso a entity get pais so funciona se o fecth type ja tiver sido inicializado
        if (entity.getPais() != null) {
            this.paisId = entity.getPais().getId();
            this.paisNome = entity.getPais().getNome();
        }
    }

    public PontoTuristicoResponse() {}

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
    public String getPaisNome() {
        return paisNome;
    }
    public void setPaisNome(String paisNome) {
        this.paisNome = paisNome;
    }
}
