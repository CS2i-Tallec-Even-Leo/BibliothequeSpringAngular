import { Particulier } from './Particulier';

export interface Etudiant extends Particulier {
  anneeUniversitaire?: number | null;
}
