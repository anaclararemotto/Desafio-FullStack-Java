import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService, PoTableAction, PoTableColumn } from '@po-ui/ng-components';
import { Pais, PaisService } from '../service/pais.service';

@Component({
	selector: 'app-paises',
	templateUrl: './paises.component.html',
	styleUrls: ['./paises.component.css']
})
export class PaisesComponent implements OnInit {
	lsActions: PoTableAction[] = this.carregarActions();
	lsColumns: PoTableColumn[] = this.carregarColunas();
	lsPaises: Pais[] = []

	constructor(
		private paisService: PaisService,
		private poNotification: PoNotificationService,
		private router: Router,
		private activatedRoute: ActivatedRoute) { }

	ngOnInit(): void {
		this.carregarPaises()
	}

	carregarActions(): PoTableAction[] {
		return [
			{
				label: 'Editar',
				icon: 'po-icon-edit',
				action: (row: Pais)=>{ this.navegarParaCadastro(row.id.toString()) }
			},
			{
				label: 'Excluir',
				icon: 'po-icon-delete',
				action: (row: Pais)=>{ this.deletarCadastro(row.id) }
			}
		]
	}

	deletarCadastro(id: number): void {
		this.paisService.deletarPais(id).subscribe({
			next: ()=>{
				this.poNotification.success('Registro excluido com sucesso!');
				this.carregarPaises();
			},
			error: (error)=>{
				this.poNotification.error(error.error?.message || 'Erro ao excluir registro');
			}
		})
	}

	navegarParaCadastro(codigoPais: string = ""){
		this.router.navigate(['cadastro', codigoPais], { relativeTo: this.activatedRoute })
	}

	carregarPaises(){
	 this.paisService.getPaises().subscribe({
			next: (data)=>{
				this.lsPaises = data;
				},
        error: (error) => {
          console.error('Erro ao carregar paises: ', error);
        }
      })
    }


	carregarColunas(): Array<PoTableColumn>{
		return [
			{
				property: 'ddi',
				label: 'DDI'
			},
			{
				property: 'nome',
				label: 'Nome',
			},
			{
				property: 'sigla',
				label: 'Sigla'
			},
			{
				property: 'continente',
				label: 'Continente'
			}
		]
	}
}

