import { Particulier } from './Particulier';

export interface Enseignant extends Particulier {
  codeDepartement?: number | null;
}
