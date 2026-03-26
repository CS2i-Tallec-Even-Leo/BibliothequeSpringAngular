import { Ressource } from './Ressource';

export interface Revue extends Ressource {
  numero?: number;
  dateParution?: Date;
}
