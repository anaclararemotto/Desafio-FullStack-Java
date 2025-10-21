import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Comentario, ComentarioRequest } from "./ponto-turistico.service";

@Injectable({
  providedIn: 'root'
})

export class ComentarioService {

  private baseUrl = 'http://localhost:8080/api/pontos-turisticos';

  constructor(private http: HttpClient) {}

  //busca comentario por id
  getComentariosByPontoTuristico(pontoTuristicoId: number): Observable<Comentario[]> {
    return this.http.get<Comentario[]>(`${this.baseUrl}/${pontoTuristicoId}/comentarios`);
  }

  //cria um novo comentario
  criarComentario(pontoTuristicoId: number, request: ComentarioRequest): Observable<Comentario> {
    return this.http.post<Comentario>(`${this.baseUrl}/${pontoTuristicoId}/comentarios`, request);
  }

  //deleta comentario
  deletarComentario(pontoTuristicoId: number, comentarioId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${pontoTuristicoId}/comentarios/${comentarioId}`)
  }
}
