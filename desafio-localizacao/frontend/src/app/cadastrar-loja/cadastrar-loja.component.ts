import { Component, OnInit } from '@angular/core';
import { LojaDTO } from '../shared/loja';
import { FormControl } from '@angular/forms';
import { LojaClientService } from '../client-rest/loja-client.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-cadastrar-loja',
  templateUrl: './cadastrar-loja.component.html',
  styleUrls: ['./cadastrar-loja.component.css']
})
export class CadastrarLojaComponent implements OnInit {
    
  id: any;
  lojaSelecionada: LojaDTO;

  name = new FormControl('');

  latitude = new FormControl('');

  longitude = new FormControl('');

  ngOnInit(): void {
  }
  
  constructor(public restApi: LojaClientService, 
    private route: ActivatedRoute,
    private router: Router){ 
    this.id = this.route.snapshot.params['id'];
   
    console.log("ID: " + this.id)
    if(isNaN(this.id)){
      console.log("Cadastro de novo funcionario");
    }else{
      console.log("Edição de usuário");
      console.log("RE:" + (typeof this.id == 'number'));
      this.getLoja();
      
    }
  }

  getLoja() {
    this.restApi.getLoja(this.id).subscribe((data: any) => {
      this.lojaSelecionada = data;
      this.name.setValue(this.lojaSelecionada.name);
      this.latitude.setValue(this.lojaSelecionada.latitude);
      this.longitude.setValue(this.lojaSelecionada.longitude);
      console.log("LAT: " + this.lojaSelecionada.latitude)
      console.log("LONG: " + this.lojaSelecionada.longitude)
   });
  }

  adicionarLoja(){
    let novaLoja: LojaDTO = {id: undefined, name: this.name.value, latitude: this.latitude.value, longitude: this.longitude.value};
    console.log(novaLoja);
    this.restApi.salvarLoja(novaLoja).subscribe((data: any) => {
      console.log(data);
    });
  }

  editarLoja(){
    console.log("Edição de usuário");
    this.lojaSelecionada.name = this.name.value;
    this.lojaSelecionada.latitude = this.latitude.value;
    this.lojaSelecionada.longitude = this.longitude.value;

    this.restApi.editarLoja(this.lojaSelecionada).subscribe((data: any) => {
      console.log("Editar-rest")
      console.log(data);
    });

    this.lojaSelecionada = undefined;
    this.id = undefined;
    this.name.setValue(undefined);
    this.latitude.setValue(undefined);
    this.longitude.setValue(undefined);

  }

  getId(){
    if(isNaN(this.id)){
      console.log("LA1")
      return undefined;
      
    }else{
      console.log("LA2");
      return this.id;
      
    }
  }



}

