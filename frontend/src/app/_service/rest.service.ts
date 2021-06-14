import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Managers} from '../_model/managers';
import {Observable, of} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class RestService {
  // httpOptions = {
  //   headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  // };
   usename: string;
   usersurname: string;
   userlogin: string;
   userNumber: string;
   userRole: string;
   constructor(private http: HttpClient) { }
  url = 'http://localhost:8081/admin/search';
  getManagers(): Observable<any> {
    return this.http.get<Managers[]>(this.url);
  }
  addMember(manager): Observable<Managers>{
    return this.http.post<Managers>(this.url, manager);

  }
  deleteUser(id: number): Observable<Managers>{
    return this.http.delete<Managers>(this.url + '/' + id);
  }
}
