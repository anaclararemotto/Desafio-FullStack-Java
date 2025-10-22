import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { PoComboOption } from "@po-ui/ng-components";
import { map, Observable } from "rxjs";

export interface Pais {
  id: number;
  nome: string;
  sigla: string;
  continente: string;
  ddi: number;
}

export interface PaisRequest {
  nome:string;
  sigla: string;
  continente: string;
  ddi: number
}

@Injectable({
  providedIn: 'root'
})

export class PaisService {

  private apiUrl = 'http://localhost:8080/api/paises'

  constructor(private http: HttpClient) {}


  //get /api/paises
    getPaises(): Observable<Pais[]> {
      return this.http.get<Pais[]>(this.apiUrl);
    }

    // get pais por id
    getPais(id: number): Observable<Pais> {
      return this.http.get<Pais>(`${this.apiUrl}/${id}`);
    }

    //add pais
    criarPais(pais: PaisRequest):Observable<Pais> {
      return this.http.post<Pais>(this.apiUrl, pais)
    }

    //atualiza pais
    atualizarPais(id:number, pais: PaisRequest): Observable<Pais> {
      return this.http.put<Pais> (`${this.apiUrl}/${id}`, pais)
    }

    // deleta pais
    deletarPais(id: number): Observable<any> {
      return this.http.delete(`${this.apiUrl}/${id}`)
    }

    //converte lista de paises para o padrao po-select
    getPaisesAsOption(): Observable<PoComboOption[]> {
      return this.getPaises().pipe(
        map(paises => paises.map(pais => ({label: pais.nome, value: pais.id})))
      );
    }
}
