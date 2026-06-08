import { Stockage } from './Stockage';

export interface Ressource {
  id: number;
  titre: string;
  caution: number;
  codeBarre: string;
  stockage?: Stockage;
}
