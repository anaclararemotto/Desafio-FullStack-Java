import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService, PoPageAction } from '@po-ui/ng-components';
import { ComentarioService } from 'src/app/service/comentario.service';
import { Comentario, ComentarioRequest, PontoTuristico, PontoTuristicoService } from 'src/app/service/ponto-turistico.service';

@Component({
  selector: 'app-detalhes-ponto-turistico',
  templateUrl: './detalhes-ponto-turistico.component.html',
  styleUrls: ['./detalhes-ponto-turistico.component.css']
})
export class DetalhesPontoTuristicoComponent implements OnInit {

  pontoTuristicoId!: number;
  //detalhes do ponto turistico principal
  pontoTuristico!: PontoTuristico;
  //Lista de comentarios
  comentarios: Comentario[] = [];

  comentarioForm!: FormGroup;

  acoes: PoPageAction[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private pontoTuristicoService: PontoTuristicoService,
    private comentarioService: ComentarioService,
    private poNotification: PoNotificationService
  ) {}

  ngOnInit() {
    // captura o id da url
    this.pontoTuristicoId = +this.route.snapshot.paramMap.get('id')!;
    this.setupComentatioForm();
    this.carregarDetalhes();
  }

  private setupComentatioForm() {
    this.comentarioForm = this.fb.group({
      autor: ['', Validators.required],
      textoComentario: ['', Validators.required],
    });
  }

  //botoes de açoes
  private setupAcoes() {
    this.acoes = [
      {
        label: 'Editar Ponto',
        action: this.editarPonto.bind(this),
        icon: 'po-icon-edit',
      },
      {
        label: 'Voltar',
        action: () => this.router.navigate(['/ponto-turistico']),
        icon: 'po-icon-left',
      },
    ];
  }

  carregarDetalhes() {
    //carregar detalhes do ponto
    this.pontoTuristicoService
      .getPontoTuristico(this.pontoTuristicoId)
      .subscribe({
        next: (ponto) => {
          this.pontoTuristico = ponto;
          this.setupAcoes();
          this.carregarComentarios();
        },
        //lança um erro se nao achar um ponto
        error: (err) => {
          this.poNotification.error('Ponto turistico não encontrado');
          this.router.navigate(['/ponto-turistico']);
        },
      });
  }

  //metodo de reotnar os comentarios
  carregarComentarios() {
    this.comentarioService
      .getComentariosByPontoTuristico(this.pontoTuristicoId)
      .subscribe({
        next: (data) => {
          this.comentarios = data;
        },
        //lança um erro se nao achar um ponto
        error: (err) => {
          console.error('Erro ao carregar comentarios:', err);
        },
      });
  }
  //metodo de editar op ponto turistico
  editarPonto(){
    this.router.navigate(['ponto-turistico/cadastro/edit', this.pontoTuristicoId]);
  }

  //enviar um novo comentario
  enviarComentario() {
    if(this.comentarioForm.invalid){
      this.poNotification.error('Preencha nome e comentario');
      return;
    }

    const request: ComentarioRequest = {
      ...this.comentarioForm.value,
      pontoTuristicoId: this.pontoTuristicoId
    };

    this.comentarioService.criarComentario(this.pontoTuristicoId, request).subscribe({
       next: () => {
          this.poNotification.success('Comentario adicionado com sucesso!')
          this.comentarioForm.reset();
          this.carregarComentarios();
        },
        //lança um erro se nao achar um ponto
        error: (err) => {
          this.poNotification.error(`Falha ao comentar: ${err.error.message || 'Erro de comunicação'}`);
        },
    })
  }

}
