import { Particulier } from './User/Particulier';
import { Exemplaire } from './Stock/Exemplaire';

export interface Emprunt {
  id?: number;
  dateEmprunt: string;
  dateRetourPrevue?: string;
  dateRetourEffectif?: string;
  statut: string;
  particulier?: Particulier;
  exemplaire?: Exemplaire;
}
