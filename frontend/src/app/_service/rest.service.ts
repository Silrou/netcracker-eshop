import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Managers} from '../_model/managers';
import {Observable, of} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class RestService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  // firstName: string;
  // lastName: string;
  // email: string;
  // phoneNumber: string;
  // role: string;
  managers: Managers [] = [];
  constructor(private http: HttpClient) { }
  url = 'http://localhost:3000/Managers';
  private addNewUrl = 'http://localhost:8081/search/new';
  private getAllUrl = 'http://localhost:8081/search/all';
  private deleteUrl = 'http://localhost:8081/search/delete';
  getManagers(): Observable<any> {
    return this.http.get<Managers[]>(this.getAllUrl);
  }
  addMember(manager: Managers): Observable<any>{
    // return this.http.post<Managers>(this.url, manager);
    return this.http.post<Managers>(this.addNewUrl, manager);
  }
  deleteUser(id: number): Observable<Managers>{
    return this.http.delete<Managers>(this.deleteUrl + '/' + id);
  }
}
