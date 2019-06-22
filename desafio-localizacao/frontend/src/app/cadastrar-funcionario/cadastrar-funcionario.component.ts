import { Component, OnInit, Input } from '@angular/core';
import { FuncionarioDTO } from '../shared/funcionario';
import { FuncionarioClientService } from '../client-rest/funcionario-client.service';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-cadastrar-funcionario',
  templateUrl: './cadastrar-funcionario.component.html',
  styleUrls: ['./cadastrar-funcionario.component.css']
})
export class CadastrarFuncionarioComponent implements OnInit {
  
  id: number;
  funcionarioEdicao: FuncionarioDTO;

  name = new FormControl('');

  latitude = new FormControl('');

  longitude = new FormControl('');

  ngOnInit(): void {
  
  }

  getId(){
    if(isNaN(this.id)){
      return undefined;
      
    }else{
      return this.id;
    }
  }
  
  constructor(public restApi: FuncionarioClientService, 
    private route: ActivatedRoute,
    private router: Router){ 
    this.id = this.route.snapshot.params['id'];
   
    console.log("ID: " + this.id)
    let val: any  = isNaN(this.id);
    console.log(val); 
    if(val){
      console.log("Cadastro de novo funcionario");
    }else{
      console.log("Edição de usuário");
      this.getFuncionario();
      
    }
  }


  adicionarFuncionario(){
    let novoFuncionario: FuncionarioDTO = {id: undefined, name: this.name.value, latitude: this.latitude.value, longitude: this.longitude.value, lojas: undefined};
    console.log("Add novo");
    console.log(novoFuncionario);
    this.restApi.salvarFuncionario(novoFuncionario).subscribe((data: any) => {
      console.log(data);
    });

  }

  editarFuncionario(){
    console.log("Edição de usuário");
    this.funcionarioEdicao.name = this.name.value;
    this.funcionarioEdicao.latitude = this.latitude.value;
    this.funcionarioEdicao.longitude = this.longitude.value;

    this.restApi.editarFuncionario(this.funcionarioEdicao).subscribe((data: any) => {
      console.log("Editar-rest")
      console.log(data);
    });

    this.funcionarioEdicao = undefined;
    this.id = undefined;
    this.name.setValue(undefined);
    this.latitude.setValue(undefined);
    this.longitude.setValue(undefined);

  }

  getFuncionario(){
    this.restApi.getFuncionario(this.id).subscribe((data: any) => {
      this.funcionarioEdicao = data;
      this.name.setValue(this.funcionarioEdicao.name);
      this.latitude.setValue(this.funcionarioEdicao.latitude);
      this.longitude.setValue(this.funcionarioEdicao.longitude);
      console.log("LAT: " + this.funcionarioEdicao.latitude)
      console.log("LONG: " + this.funcionarioEdicao.longitude)
   });
  }




}
