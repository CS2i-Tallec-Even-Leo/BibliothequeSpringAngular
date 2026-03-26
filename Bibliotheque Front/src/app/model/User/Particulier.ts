import { Ville } from './Ville';

export interface Particulier {
  id: number;
  nom: string;
  prenom: string;
  email: string;
  telephone: string;
  adresse: string;
  dateInscription: Date;
  ville?: Ville;
}
