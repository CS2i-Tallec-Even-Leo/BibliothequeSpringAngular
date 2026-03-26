import { Particulier } from './Particulier';
import { Departement } from './Departement';

export interface Etudiant extends Particulier {
  numeroEtudiant?: string;
  departement?: Departement;
}
