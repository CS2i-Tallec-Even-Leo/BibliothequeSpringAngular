import { Particulier } from './User/Particulier';
import { Exemplaire } from './Stock/Exemplaire';

export interface Emprunt {
  id?: number;
  dateEmprunt: Date;
  dateRetourPrevue?: Date;
  dateRetourEffectif?: Date;
  statut: string;
  particulier?: Particulier;
  exemplaire?: Exemplaire;
}
