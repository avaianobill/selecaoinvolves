import { Component, OnInit } from '@angular/core';
import { LojaClientService } from '../client-rest/loja-client.service';
import { LojaDTO } from '../shared/loja';


@Component({
  selector: 'app-listagem-lojas',
  templateUrl: './listagem-lojas.component.html',
  styleUrls: ['./listagem-lojas.component.css']
})
export class ListagemLojasComponent implements OnInit {

  lojas: any = [];
  headers: String[] = ["Name", "Latitude", "Longitude"];

  constructor(public restApi: LojaClientService){ 

      for(let j = 0; j < this.lojas.length; j++){
        let head = this.headers[j];
      }

   }

  ngOnInit() {
    this.loadLojas()
  }

  loadLojas(){
      return this.restApi.getLojas().subscribe((data: {}) => {
        this.lojas = data;
    });
  } 

  delete(loja: LojaDTO){
    this.restApi.deleteLoja(loja).subscribe((data: {}) => { });
  }

}
