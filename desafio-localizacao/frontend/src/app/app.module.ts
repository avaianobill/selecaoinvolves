/// <reference types="@types/googlemaps" />

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AgmCoreModule } from '@agm/core';
import { AgmDirectionModule } from 'agm-direction';
import { AgmSnazzyInfoWindowModule } from '@agm/snazzy-info-window';

import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { ModalModule } from 'ngx-bootstrap/modal';
import { NgbModule, NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListagemFuncionariosComponent } from './listagem-funcionarios/listagem-funcionarios.component';
import { MapsComponent } from './maps/maps.component';
import { ListagemLojasComponent } from './listagem-lojas/listagem-lojas.component';
import { AccordionModule } from 'ngx-bootstrap/accordion';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { CadastrarFuncionarioComponent } from './cadastrar-funcionario/cadastrar-funcionario.component';
import { CadastrarLojaComponent } from './cadastrar-loja/cadastrar-loja.component';


@NgModule({
  declarations: [
    AppComponent,
    MapsComponent,
    ListagemFuncionariosComponent,
    ListagemLojasComponent,
    LoginComponent,
    CadastrarFuncionarioComponent,
    HomeComponent,
    CadastrarLojaComponent    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BsDropdownModule,
    TooltipModule,   
    ModalModule,
    HttpClientModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyA2x1u55py1KpjDtPRBhoG8HJtLnWtJYcc'
    }),
    AgmDirectionModule,
    AccordionModule.forRoot(),
    AgmSnazzyInfoWindowModule,
    ReactiveFormsModule
  ],  
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { 

  panelOpenState = false;

}
