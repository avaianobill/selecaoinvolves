import { Component, OnInit } from '@angular/core';
import { VinculoClientService } from '../client-rest/vinculo-client.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {

  status: string;

  constructor(public restApi: VinculoClientService){ 

  }

  ngOnInit() {  

    this.status = "";

  }

  regerar(){
    this.restApi.redefinir().subscribe((data: any) => {
      console.log("REDEFINDO");
      this.status = "Operação realizada";
   });
  }

  getStatus(){
    console.log(this.status);
    return this.status;
  }

}
