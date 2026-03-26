import { Particulier } from './Particulier';
import { Departement } from './Departement';

export interface Enseignant extends Particulier {
  specialite?: string;
  departement?: Departement;
}
