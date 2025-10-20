package br.com.juridico.totvs.fullstack.Backend.domain;

import br.com.juridico.totvs.fullstack.Backend.service.dto.PaisDTO;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pais {
    @Id
    private Long id;
    private String nome;
    private String sigla;
    private String continente;
    private int ddi;

    public Pais(Long id, String nome, String sigla, String continente, int ddi){
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.continente = continente;
        this.ddi = ddi;
    }

    public Pais(PaisDTO paisDTO){
        this.id = paisDTO.getId();
        this.nome = paisDTO.getNome();
        this.sigla = paisDTO.getSigla();
        this.continente = paisDTO.getContinente();
        this.ddi = paisDTO.getDdi();
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public int getDdi() {
        return ddi;
    }

    public void setDdi(int ddi) {
        this.ddi = ddi;
    }
}
