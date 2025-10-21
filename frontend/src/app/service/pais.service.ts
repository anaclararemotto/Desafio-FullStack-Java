import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { PoComboOption } from "@po-ui/ng-components";
import { map, Observable } from "rxjs";

export interface Pais {
  id: number;
  nome: string;
  sigla: string;
  continente: string;
  dii: number;
}

@Injectable({
  providedIn: 'root'
})

export class PaisService {

  private apiUrl = 'http://localhost:8080/api/pais'

  constructor(private http: HttpClient) {}


  //get /api/paises
    getPaises(): Observable<Pais[]> {
      return this.http.get<Pais[]>(this.apiUrl);
    }

    //converte lista de paises para o padrao po-select
    getPaisesAsOption(): Observable<PoComboOption[]> {
      return this.getPaises().pipe(
        map(paises => paises.map(pais => ({label: pais.nome, value: pais.id})))
      );
    }
}
