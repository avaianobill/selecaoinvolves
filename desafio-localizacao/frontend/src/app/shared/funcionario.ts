import { LojaDTO } from '../shared/loja'

export class FuncionarioDTO {

  id: number;
  name: String;
  latitude: number;
  longitude: number;
  lojas: LojaDTO[];

}
