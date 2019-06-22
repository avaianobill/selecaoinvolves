/// <reference types="@types/googlemaps" />

import { Component, OnInit, Input } from '@angular/core';

import { FuncionarioDTO } from '../shared/funcionario';
import { LojaDTO } from '../shared/loja';
import { CoordenadaDTO } from '../shared/coordenada';
import { LatLng, LatLngLiteral } from '@agm/core';

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.css']
})
export class MapsComponent implements OnInit {


  @Input() funcionarioSelecionado: FuncionarioDTO;

  origem: LatLngLiteral;

  locals: LatLngLiteral[];
  coordenadas: CoordenadaDTO[] = [];

  lat:number = -27.5940513;
  lng:number = -48.5587729;

  zoom: number = 15;

  constructor() { }

  ngOnInit() { }

  ngOnChanges(changes) {
    this.onChange(); 
  }
  
  onChange(){

    this.origem = null;
    this.locals = [];
    this.coordenadas = [];

    if(this.funcionarioSelecionado === undefined){

    }else{
      var qtdLojas = this.funcionarioSelecionado.lojas.length;

      var origem: LatLngLiteral = {lat: this.funcionarioSelecionado.latitude, lng: this.funcionarioSelecionado.longitude};
      console.log(origem);
      this.locals.push(origem);

      this.lat = origem.lat;
      this.lng = origem.lng;

      console.log("LOJAS");

      if(qtdLojas > 0){
        for(let i = 0; i < qtdLojas; i++){
          let destino: LatLngLiteral = {lat: this.funcionarioSelecionado.lojas[i].latitude, lng: this.funcionarioSelecionado.lojas[i].longitude};
          console.log(destino);
          console.log(destino.lat);
          this.locals.push(destino);
        }

        this.origem = this.locals[0];
        for(let i = 1; i < this.locals.length; i++){
          if(this.funcionarioSelecionado.lojas[i] !== undefined){
            this.coordenadas.push({name: this.funcionarioSelecionado.lojas[i].name, origem: this.locals[i], destino: null});
          }
            
        }
      }
    }

    

  }


}
