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













}
