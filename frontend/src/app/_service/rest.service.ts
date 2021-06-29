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
  managers: Managers;
  constructor(private http: HttpClient) { }
  url = 'http://localhost:8081/admin/search/';
  getManagers(): Observable<any> {
    return this.http.get<Managers[]>(this.url);
  }
  addMember(manager): Observable<Managers>{
    return this.http.post<Managers>(this.url, manager);

  }
  deleteUser(id: number): Observable<Managers>{
    return this.http.delete<Managers>(this.url + '/' + id);
  }
  getManager(): Observable<any> {
    const TUrll = 'http://localhost:8081/admin/search/manager';
    const nUrl = `${TUrll}`;
    return this.http.get(nUrl);
  }
  getCorier(): Observable<any> {
    const TUrll = 'http://localhost:8081/admin/search/courier';
    const nUrl = `${TUrll}`;
    return this.http.get(nUrl);
  }
  getByName(name): Observable<any> {
    const TUrll = 'http://localhost:8081/admin/getByName/' + name ;
    const nUrl = `${TUrll}`;
    return this.http.get(nUrl);
  }
  getOnDutyNow(): Observable<any> {
    const TUrll = 'http://localhost:8081/admin/onDuty';
    const nUrl = `${TUrll}`;
    return this.http.get(nUrl);
  }
  getTask(id): Observable<any> {
    const TUrll = 'http://localhost:8081/courier/cabinet/get/' + id;
    const nUrl = `${TUrll}`;
    return this.http.get(nUrl);
  }
  setStatus(cartId): Observable<any> {
    const TUrll = 'http://localhost:8081/courier/cabinet/' + cartId;
    const nUrl = `${TUrll}`;
    return this.http.get(nUrl);
  }
}
