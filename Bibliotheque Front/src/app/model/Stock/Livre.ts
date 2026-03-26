import { Auteur } from './Auteur';

export interface Livre {
  isbn: string;
  auteur: Auteur;
  genre: string;
}
