import { Stockage } from './Stockage';

export interface Livre {
  id: number;
  titre: string;
  caution: number;
  codeBarre: string;
  iSBN?: string;
  auteur?: string;
  genre?: string;
  anneePublication?: number;
  stockage?: Stockage;
}
