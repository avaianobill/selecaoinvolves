import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError, take} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class VinculoClientService {

  apiURL: string = 'http://localhost:8080/vinculos?action=redefinir';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
    })
  }   

  redefinir(){
    return this.http.get(this.apiURL).pipe(retry(1), catchError(this.handleError))
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
