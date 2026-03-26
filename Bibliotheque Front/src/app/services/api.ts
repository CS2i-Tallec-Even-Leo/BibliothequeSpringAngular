import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Bibliotequaire } from '../model/Bibliotequaire';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private apiUrl = 'http://localhost:3000'; // ton API locale

  constructor(private http: HttpClient) {}
}

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {}

  getUsers(): Observable<Bibliotequaire[]> {
    return this.http.get<Bibliotequaire[]>(this.apiUrl);
  }

  createUser(bibliotequaire: Bibliotequaire): Observable<Bibliotequaire> {
    return this.http.post<Bibliotequaire>(this.apiUrl, bibliotequaire);
  }
}
