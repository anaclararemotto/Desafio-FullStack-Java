import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService } from '@po-ui/ng-components';
import { Pais, PaisRequest, PaisService } from 'src/app/service/pais.service';

@Component({
	selector: 'app-cadastro-paises',
	templateUrl: './cadastro-paises.component.html',
	styleUrls: ['./cadastro-paises.component.css']
})
export class CadastroPaisesComponent implements OnInit {
	idPais!: number | null;
	formPais: FormGroup;
	title: string = "Novo cadastro de País"

	constructor(
    private formBuilder: FormBuilder,
		private poNotification: PoNotificationService,
		private route: ActivatedRoute,
		private router: Router,
		private paisService: PaisService
		) {

		this.formPais = this.formBuilder.group({
      id: [null],
			nome: ['', Validators.compose([Validators.required])],
			sigla: ['', Validators.compose([Validators.required])],
			continente: ['', Validators.compose([Validators.required])],
			ddi: [, Validators.compose([Validators.required])]
		})
	}

	ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('idPais');
		this.idPais = idParam ? +idParam : null;
		if (this.idPais !== null){
			this.buscaDadosPais()
			this.title = "Alteração do País"
		}
	}

	salvar(){
		if (this.formPais.invalid){
			this.poNotification.error('Preencha todos os campos antes de salvar as alterações!')
      return
		}

    //mapeia os val do form
    const paisData: Pais = this.formPais.value;

    if(this.idPais === null) {
      this.enviarPost(paisData);
    } else {
      this.enviarPut(this.idPais, paisData)
    }
	}

	voltar(){
		this.router.navigate(['../'], { relativeTo: this.route })
	}

	enviarPost(paisData: PaisRequest){
		this.paisService.criarPais(paisData).subscribe({
			next:() => {
				this.poNotification.success("Registro criado com sucesso!");
				this.voltar();
			},
			error:(erro) => {
				this.poNotification.error(erro.error?.message || 'Erro ao criar o registro')
			},
		})
	}

	enviarPut(id: number, paisData: PaisRequest){
		this.paisService.atualizarPais(id, paisData).subscribe({
			next:() => {
				this.poNotification.success("Registro atualizado com sucesso!");
				this.voltar();
			},
			error:(erro) => {
				this.poNotification.error(erro.error?.message || 'Erro ao atualizar o registro')
			},
		})
	}

	buscaDadosPais(){
		this.paisService.getPais(this.idPais!).subscribe({
			next: (resposta: Pais)=>{
				this.formPais.patchValue({
          id: resposta.id,
					nome: resposta.nome,
					sigla: resposta.sigla,
					continente: resposta.continente,
					ddi: resposta.ddi
				})
			},
			error: (erro)=>{
				this.poNotification.error(erro.error?.message || 'Erro ao buscar dados do pais')
        this.voltar();
			}
		})
	}
}
