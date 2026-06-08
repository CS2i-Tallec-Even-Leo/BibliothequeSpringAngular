import { Ressource } from './Ressource';

export interface Exemplaire {
  id?: number;
  numeroExemplaire: string;
  etat: string;
  ressource?: Ressource;
  dateAcquisition?: string;
}
