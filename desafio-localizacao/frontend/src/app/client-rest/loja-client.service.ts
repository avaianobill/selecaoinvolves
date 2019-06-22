import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError, take} from 'rxjs/operators';
import { LojaDTO } from '../shared/loja'

@Injectable({
  providedIn: 'root'
})
export class LojaClientService {
  
  lojaSelecionada: LojaDTO;

  apiURL: String = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
    })
  }   

  getLojas(): Observable<LojaDTO> {
    return this.http.get<LojaDTO>(this.apiURL + '/lojas').pipe(retry(1), catchError(this.handleError))
  }

  getLoja(id: number) {
    return this.http.get<LojaDTO>(this.apiURL + '/lojas/' + id).pipe(retry(1), catchError(this.handleError))
  }

  salvarLoja(loja: LojaDTO) {
    return this.http.post<LojaDTO>(this.apiURL + '/lojas', loja).pipe(take(1));
  }

  editarLoja(lojaSelecionada: LojaDTO) {
    return this.http.put<LojaDTO>(this.apiURL + '/lojas/' + lojaSelecionada.id, lojaSelecionada).pipe(take(1));
  }

  deleteLoja(loja: LojaDTO) {
    return this.http.delete(this.apiURL + '/lojas/' + loja.id).pipe(take(1));
  }

  setData (data) {
    this.lojaSelecionada = data;
  }
  getData () {
    return this.lojaSelecionada;
  }

  handleError(error) {
     let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
}
