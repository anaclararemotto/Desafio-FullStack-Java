import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PoComboOption, PoNotificationService, PoPageAction } from '@po-ui/ng-components';
import { Observable } from 'rxjs';
import { PaisService } from 'src/app/service/pais.service';
import { PontoTuristico, PontoTuristicoRequest, PontoTuristicoService } from 'src/app/service/ponto-turistico.service';

@Component({
  selector: 'app-cadastro-pontos-turisticos',
  templateUrl: './cadastro-pontos-turisticos.component.html',
  styleUrls: ['./cadastro-pontos-turisticos.component.css']
})
export class CadastroPontosTuristicosComponent implements OnInit {

  //gerencia o estado e a validação do form
  pontoForm!: FormGroup;

  //titulo da pag
  title: string = 'Novo Ponto Turistico';

  // detecta o modo de edição
  isEdit: boolean = false;

  //opçoes do back para popular o po-select de pais
  paisesOptions: PoComboOption[] = [];

  //opçoes para o po-select de melhor estacao
  estacoesOptions: PoComboOption[] = [
    {label: 'Primavera', value: 'PRIMAVERA'},
    {label: 'Verão', value: 'VERAO'},
    {label: 'Outono', value: 'OUTONO'},
    {label: 'Inverno', value: 'INVERNO'},
    {label: 'O ano todo', value: 'O_ANO_TODO'}
  ];

  // botoes salvar e cancelar
  acoes: PoPageAction[] = [];

  constructor(
    //contrui form reativo
    private fb: FormBuilder,
    //le o parametro id da URL
    private activatedRoute: ActivatedRoute,
    private router : Router,
    //exibe menasagem ao user
    private poNofitication: PoNotificationService,
    private pontoTuristicoService: PontoTuristicoService,
    //busca paises
    private paisService: PaisService
  ) { }

  ngOnInit(): void {
    this.setupForm();
    this.carregarPaises();
    this.checarModoEdicao();
  }

  //configura form group r form controld, aplicando as validaçoes.
  private setupForm() {
    this.pontoForm = this.fb.group({
      //id eh null na criação mas eh preenchido na edição
      id: [null],
      nome: ['', Validators.required],
      cidade: ['', Validators.required],
      //paisId eh id numerico obrigatorio
      paisId: [null, Validators.required],
      melhorEstacao: [null, Validators.required],
      resumo: ['', Validators.required]
    });
    this.setupAcoes();
  }

  //configura salvar e cancelar na pag
  private setupAcoes() {
    this.acoes = [
      {label: 'Salvar', action: () => this.salvar(), icon: 'po-icon-ok'},
      {label: 'Cancelar', action: () => this.router.navigate(['/ponto-turistico']), icon: 'po-icon-close'},
    ]
  }

  //Busca a lista de paises no back e converte para o formato do po-select
  private carregarPaises() {
    this.paisService.getPaisesAsOption().subscribe(options => {
      this.paisesOptions = options;
    })
  }

  //verifica url para entrar no modo de edição
  private checarModoEdicao() {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.title = 'Editar Ponto Turistico';

      this.pontoTuristicoService.getPontoTuristico(+id).subscribe(ponto => {
        //puxa os dados do form pelo back
        this.pontoForm.patchValue({
          ...ponto,
          paisId: ponto.paisId
        });
      });
    }
  }

  salvar() {
    if (this.pontoForm.invalid) {
      //notifica o user sobre a falha na validacao do front
      this.poNofitication.error('Preencha todos os campos obrigatórios.');
      return;
    }

    //pega os valores do form e tipa com ponto turistico request
    const pontoRequest: PontoTuristicoRequest = this.pontoForm.value;

    // Variavel para armazenar POST ou PUT
    let operacao$: Observable<PontoTuristico>;

    // verifica se é uma edição
    if (this.isEdit && pontoRequest.id) {
      // se  edição chama o PUT
      operacao$ = this.pontoTuristicoService.atualizarPontoTuristico(pontoRequest.id, pontoRequest);
    } else {
      // se for criação chama o POST
      operacao$ = this.pontoTuristicoService.criarPontoTuristico(pontoRequest);
    }

    operacao$.subscribe({
      next: () => {
        //redirecionamento para a listagem
        this.poNofitication.success(`Ponto turistico ${this.isEdit ? 'atualizado' : 'criado'} com sucesso!`);
        this.router.navigate(['/ponto-turistico']);
      },
      error: (err) => {
        console.error('Erro ao salvar ponto turístico:', err);
        //notificação de erro com msg do back
        this.poNofitication.error(`Erro ao salvar: ${err.error?.message || 'Falha na comunicação com o servidor'}`);
      }
    });

    // this.pontoTuristicoService.criarPontoTuristico(pontoRequest).subscribe({
    //   next: () => {
    //     //Notificação de sucesso e redirecionamento para a listagem
    //     this.poNofitication.success(`Ponto turistico ${this.isEdit ? 'atualizado' : 'criado'} com sucesso!`);
    //     this.router.navigate(['/ponto-turistico']);
    //   },
    //   error: (err) => {
    //     //notificação de erro com msg do back
    //     this.poNofitication.error(`Erro ao salvar: ${err.error.message || 'Falha na comunicação'}`);
    //   }
    // });
  }

}
