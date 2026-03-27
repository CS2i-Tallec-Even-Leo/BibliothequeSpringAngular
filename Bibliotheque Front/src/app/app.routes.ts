import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Connexion } from './connexion/connexion';
import { ConnexionAdmin } from './connexion-admin/connexion-admin';
import { CreateUser } from './create-user/create-user';
import { ListUsers } from './list-users/list-users';
import { ListRessources } from './list-ressources/list-ressources';
import { EmpruntPage } from './emprunt/emprunt';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: Home },
  { path: 'connexion', component: Connexion },
  { path: 'connexion-admin', component: ConnexionAdmin },
  { path: 'create-user', component: CreateUser },
  { path: 'list-users', component: ListUsers },
  { path: 'list-ressources', component: ListRessources },
  { path: 'emprunts', component: EmpruntPage },
  { path: '**', redirectTo: 'home' },
];
