import { Particulier } from './Particulier';

export interface Enseignant extends Particulier {
  code_departement: number;
}
