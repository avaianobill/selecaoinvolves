import { LatLngLiteral } from '@agm/core';

export class CoordenadaDTO {

    name: String
    origem: LatLngLiteral;
    destino: LatLngLiteral;

    constructor(name: String, origem: LatLngLiteral, destino: LatLngLiteral){
        this.name = name;
        this.origem = origem;
        this.destino = destino;
    }

    
  
  }
  