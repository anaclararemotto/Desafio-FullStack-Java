import { Component, OnInit } from '@angular/core';
import {
  PontoTuristico,
  PontoTuristicoService,
} from '../service/ponto-turistico.service';
import { PoPageAction, PoTableColumn } from '@po-ui/ng-components';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pontos-turisticos',
  templateUrl: './pontos-turisticos.component.html',
  styleUrls: ['./pontos-turisticos.component.css'],
})
export class PontosTuristicosComponent implements OnInit {
  //armazena os pontos retornados pelo backend
  pontosTuristicos: PontoTuristico[] = [];
  //Configuração das colunas da tabela com PO-UI
  colunas: PoTableColumn[] = [];
  //Acoes da pag que aparecem no cabeçalho do po-page
  acoes: PoPageAction[] = [];

  //realiza chamada HTTP e injeta o router de navegação de telas
  constructor(
    private pontoTuristicoService: PontoTuristicoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.setupAcoes();
    this.setupColunas();
  }

  //configura as açoes do cabeçalho da pag
  private setupAcoes() {
    this.acoes = [
      {
        label: 'Novo ponto turistico',
        //navega para o formulario
        action: () => this.router.navigate(['/pontos-turisticos/new']),
        icon: 'po-icon-plus',
      },
    ];
  }

  private setupColunas() {
    this.colunas = [
      { property: 'nome', label: 'Ponto Turistico', type: 'string' },
      { property: 'cidade', label: 'Cidade', type: 'string' },
      { property: 'paisNome', label: 'País', type: 'string' },
      //mapeamento visual do enum de melhor estação
      {
        property: 'melhorEstacao',
        label: 'Melhor Estação',
        type: 'label',
        labels: [
          { value: 'PRIMAVERA', label: 'Primavera', color: 'color-10' },
          { value: 'VERAO', label: 'Verão', color: 'color-07' },
          { value: 'OUTONO', label: 'Outono', color: 'color-09' },
          { value: 'INVERNO', label: 'Inverno', color: 'color-01' },
          { value: 'O_ANO_TODO', label: 'O ano todo', color: 'color-08' },
        ],
      },
      //coluna de açoes para visualizar detalhes
      {
        property: 'acoes',
        label: 'Ações',
        type: 'icon',
        icons: [
          {
            action: this.visualizarDetalhes.bind(this),
            icon: 'po-icon-eye',
            value: 'view',
            tooltip: 'Visualizar detalhes e comentários'
          }
        ]
      }
    ];
  }

  visualizarDetalhes(item: PontoTuristico) {
    this.router.navigate(['/pontos-turisticos/detail', item.id]);
  }
}
