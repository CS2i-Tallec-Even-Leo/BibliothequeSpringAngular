import { Auteur } from './Auteur';

export interface Livre {
  id?: number;
  titre: string;
  caution: number;
  codeBarre: string;
  isbn: string;
  auteur?: Auteur;
  genre: string;
}
