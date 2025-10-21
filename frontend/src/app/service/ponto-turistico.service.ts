import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { callbackify } from "util";

// estrutura de dados esperada pelo backend
export interface PontoTuristico {
  id: number;
  nome: string;
  cidade: string;
  resumo: string;
  melhorEstacao: 'VERAO' | 'OUTONO' | 'INVERNO' | 'PRIMAVERA' | 'O_ANO_TODO';
  paisId: number;
  paisNome: string;
}

export interface Comentario {
  id: number;
  autor: string;
  textoComentario: string;
  dataCriacaoFormatada: string;
  pontoTuristicoId: number;
}

//estrutura a ser enviada ao baxkend
export interface PontoTuristicoRequest {
  id?: number;
  nome: string;
  cidade: string;
  resumo: string;
  melhorEstacao: string;
  paisId: number;
}

export interface ComentarioRequest {
  autor: string;
  texttoComentario: string;
  pontoTuristicoId: number;
}

@Injectable({
  providedIn: 'root'
})

export class PontoTuristicoService{

  //url base dos endpoints criados
  private apiUrl = 'http://localhost:8080/api/pontos-turisticos';

  constructor(private http: HttpClient) {}

  //lista de todos os pontos turisticos
  getPontosTuristicos(): Observable<PontoTuristico[]> {
    return this.http.get<PontoTuristico[]>(this.apiUrl);
  }

  //busca ponto por id
  getPontoTuristico (id: number): Observable<PontoTuristico> {
    return this.http.get<PontoTuristico>(`${this.apiUrl}/${id}`);
  }

  //cria novo ponto turistico
  criarPontoTuristico(ponto: PontoTuristicoRequest): Observable<PontoTuristico> {
    return this.http.post<PontoTuristico>(this.apiUrl, ponto)
  }

  //atualiza um existente
  atualizarPontoTuristico(id: number, ponto: PontoTuristicoRequest): Observable<PontoTuristico> {
    return this.http.put<PontoTuristico>(`${this.apiUrl}/${id}`, ponto)
  }

  //deleta um ponto turitico
  deletarPontoTuristico(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`)
  }

}
