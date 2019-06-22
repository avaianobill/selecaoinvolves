import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError} from 'rxjs';
import { retry, catchError, take } from 'rxjs/operators';
import { FuncionarioDTO } from '../shared/funcionario'

@Injectable({
  providedIn: 'root'
})
export class FuncionarioClientService {
    
  funcionarioSelecionado: FuncionarioDTO;

  apiURL: String = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
    })
  }   

  getFuncionarios(): Observable<FuncionarioDTO> {
    return this.http.get<FuncionarioDTO>(this.apiURL + '/funcionarios').pipe(retry(1), catchError(this.handleError))
  }

  getFuncionario(id: number) {
    return this.http.get<FuncionarioDTO>(this.apiURL + '/funcionarios/' + id).pipe(retry(1), catchError(this.handleError))
  }

  salvarFuncionario(funcionario: FuncionarioDTO) {
    return this.http.post<FuncionarioDTO>(this.apiURL + '/funcionarios', funcionario).pipe(take(1));
  }

  editarFuncionario(funcionarioSelecionado: FuncionarioDTO) {
    return this.http.put<FuncionarioDTO>(this.apiURL + '/funcionarios/' + funcionarioSelecionado.id, funcionarioSelecionado).pipe(take(1));
  }

  deleteFuncionario(funcionarioSelecionado: FuncionarioDTO) {
    return this.http.delete(this.apiURL + '/funcionarios/' + funcionarioSelecionado.id).pipe(take(1));
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
