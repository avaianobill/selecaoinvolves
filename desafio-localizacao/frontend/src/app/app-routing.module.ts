import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ListagemFuncionariosComponent } from './listagem-funcionarios/listagem-funcionarios.component';
import { ListagemLojasComponent } from './listagem-lojas/listagem-lojas.component';
import { CadastrarFuncionarioComponent } from './cadastrar-funcionario/cadastrar-funcionario.component';
import { CadastrarLojaComponent } from './cadastrar-loja/cadastrar-loja.component';

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "funcionarios", component: ListagemFuncionariosComponent},
  {path: "cadastro-funcionarios/:id", component: CadastrarFuncionarioComponent},
  {path: "cadastro-lojas/:id", component: CadastrarLojaComponent},
  {path: "cadastro-funcionarios/novo", component: CadastrarFuncionarioComponent},
  {path: "cadastro-lojas/novo", component: CadastrarLojaComponent},
  {path: "lojas", component: ListagemLojasComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
