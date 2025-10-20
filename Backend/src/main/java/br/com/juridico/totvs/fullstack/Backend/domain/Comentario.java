package br.com.juridico.totvs.fullstack.Backend.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    //nome do autor do comentario
    private String autor;

    @Column(nullable = false, columnDefinition = "TEXT")
    //comentario
    private String textoComentario;

    @Column(nullable = false)
    //data e hora do comentario
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ponto_turistico_id", nullable = false)
    private PontoTuristico pontoTuristico;

    //Contrutir padrao
    public Comentario() {}

    //Construtor comppleto
    public  Comentario (Long id, String autor, String textoComentario, LocalDateTime dataCriacao, PontoTuristico pontoTuristico) {
        this.id = id;
        this.autor = autor;
        this.textoComentario = textoComentario;
        this.dataCriacao = dataCriacao;
        this.pontoTuristico = pontoTuristico;
    }

    //preenche a data da criação automaticamente antes de salvar no bd
    @PrePersist
    public void prePersist() {
        if (this.dataCriacao == null) {
            this.dataCriacao = LocalDateTime.now();
        }
    }


}
