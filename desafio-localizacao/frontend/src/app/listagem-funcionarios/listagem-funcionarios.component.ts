import { Component, OnInit } from '@angular/core';
import { FuncionarioClientService } from '../client-rest/funcionario-client.service';
import { FuncionarioDTO } from '../shared/funcionario';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-listagem-funcionarios',
  templateUrl: './listagem-funcionarios.component.html',
  styleUrls: ['./listagem-funcionarios.component.css']
})
export class ListagemFuncionariosComponent implements OnInit {

  funcionarioSelecionado: FuncionarioDTO; 
  funcionarios: Observable<FuncionarioDTO[]>;
  headers: String[] = ["Name", "Latitude", "Longitude"];

  novoFuncionario: FuncionarioDTO;

  constructor(public restApi: FuncionarioClientService){ 

  }

  ngOnInit() {
    this.loadFuncionarios()
  }

  loadFuncionarios(){
      return this.restApi.getFuncionarios().subscribe((data: any) => {
          this.funcionarios = data;
      });
  } 

  RowSelected(u:FuncionarioDTO){
    if(u !== undefined){
      this.funcionarioSelecionado = u; 
    }
  }

  delete(funcionario: FuncionarioDTO){
    this.restApi.deleteFuncionario(funcionario).subscribe((data: {}) => {});
  }

}

