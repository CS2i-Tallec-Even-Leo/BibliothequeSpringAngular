import { Ressource } from './Ressource';

export interface Revue extends Ressource {
  numero_volume: number;
  date_parution: Date;
}
