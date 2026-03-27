import { Ressource } from './Ressource';

export interface Revue extends Ressource {
  numeroVolume?: number;
  dateParution?: Date | string;
}
