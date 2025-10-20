package br.com.juridico.totvs.fullstack.Backend.domain;

import jakarta.persistence.*;

import javax.persistence.*;


//Entidade de dominio
@Entity
//Define nome na tabela
@Table(name = "ponto_turistico")
public class PontoTuristico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //nao permite valores nulos
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cidade;

    //O enum limita a quantidade de opçoes e p tipo srting garante que a leitura sera feita em forma de texto
    @Enumerated(EnumType.STRING)
    @Column(name = "melhor_estacao", nullable = false)
    private MelhorEstacao melhorEstacao;

    //columnDefinition = "TEXT" permite textos longos
    @Column(columnDefinition = "TEXT")
    private String resumo;

    // muitos pontos para um pais
    @ManyToOne
    //cria uma chave estrangeira 'pais_id' na tabela 'ponto_turistico' garantindo que todo ponto turistico tem um pais existente
    @JoinColumn(name = "pais_id", nullable = false)
    private Pais pais;

    //Construtor padrão obrigatorio para instanciar classes
    public PontoTuristico() {}

    //Contrutor para criação da entidade
    public PontoTuristico (Long id, String nome, String cidade, MelhorEstacao melhorEstacao, String resumo, Pais pais) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.melhorEstacao = melhorEstacao;
        this.resumo = resumo;
        this.pais = pais;
    }

    //Contrutor que mapeia DTO da req
    // O obj pais é populado na camasda Service
    public  PontoTuristico(PontoTuristicoRequest request) {
        this.id = request.getId();
        this.nome = request.getNome();
        this.cidade = request.getCidade();
        this.melhorEstacao = request.getMelhosEstacao();
        this.resumo = request.getResumo();
    }












}
