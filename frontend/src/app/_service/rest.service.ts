import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Managers} from '../_model/managers';
import {Observable, of} from 'rxjs';
import * as _ from 'lodash';

import { catchError, map, tap } from 'rxjs/operators';

import {User} from '../_model/user';

import {Filters} from '../_model/filters';
import {Product} from '../_model/product';
import {CourierDto} from '../_model/courierDto';
import {Admin} from '../_model/admin';
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
  users: User [] = [];
  constructor(private http: HttpClient) { }
  private addNewUrl = 'http://localhost:8081/search/new';
  private getAllUrl = 'http://localhost:8081/search/all';
  private deleteUrl = 'http://localhost:8081/search/delete';
  private updateUrl = 'http://localhost:8081/search/edit';
  private getSearchUrl = 'http://localhost:8081/admin/get';

  url = 'http://localhost:8081/admin/search/';
  getManagers(): Observable<any> {
    return this.http.get<User[]>(this.getAllUrl);
  }
  addMember(user: User): Observable<any>{
    // return this.http.post<Managers>(this.url, manager);
    console.log(user.userName);
    return this.http.post<User>(this.addNewUrl, user);
  }
  deleteUser(id: number): Observable<any>{
    return this.http.delete<User>(this.deleteUrl + '/' + id);
  }

  updateUser(id: number, user: User): Observable<any>{
    return this.http.put<User>(this.updateUrl + '/' + id, user);
  }
  getFromUsers(admin: Admin): Observable<any> {
    const params = new HttpParams().set('Admin', JSON.stringify(admin));
    return this.http.get<User[]>(this.getSearchUrl, {params});
  }
  getTask(id): Observable<any> {
    const TUrll = 'http://localhost:8081/courier/cabinet/get/' + id;
    const nUrl = `${TUrll}`;
    return this.http.get(nUrl);
  }
  setStatus(coucab: CourierDto): Observable<any> {
    const TUrll = 'http://localhost:8081/courier/cabinet/set/';
    const nUrl = `${TUrll}`;
    const params = new HttpParams().set('courierDto', JSON.stringify(coucab));
    return this.http.get<string>(nUrl, {params});
  }

}
