import { Component, OnInit } from '@angular/core';
import { PontoTuristico, PontoTuristicoService } from '../service/ponto-turistico.service';
import { PoPageAction, PoTableColumn } from '@po-ui/ng-components';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pontos-turisticos',
  templateUrl: './pontos-turisticos.component.html',
  styleUrls: ['./pontos-turisticos.component.css']
})
export class PontosTuristicosComponent implements OnInit {

  //armazena os pontos retornados pelo backend
  pontosTuristicos: PontoTuristico[] = [];
  //Configuração das colunas da tabela com PO-UI
  colunas: PoTableColumn[] = [];
  //Acoes da pag que aparecem no cabeçalho do po-page
  acoes: PoPageAction[] = [];

  //realiza chamada HTTP e injeta o router de navegação de telas
  constructor( private pontoTuristicoService : PontoTuristicoService, private router : Router) { }

  ngOnInit(): void {
    this.setupAcoes();
  }

  //configura as açoes do cabeçalho da pag
  private setupAcoes(){
    this.acoes = [{
      label: 'Novo ponto turistico',
      //navega para o formulario
      action: () => this.router.navigate(['/pontos-turisticos/new']),
      icon: 'po-icon-plus'
    }]
  }

}
