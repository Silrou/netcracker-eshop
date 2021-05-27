import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../_model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
 private getUrl = 'http://localhost:8081/user/???';
  private updateUrl = 'http://localhost:8081/user/update';
  constructor(private http: HttpClient) { }
  getUserInfo(): Observable<any>{
    return this.http.get(this.getUrl);
  }
  sendUserInfo(user: User): Observable<any>{
    return this.http.put(this.updateUrl, JSON.stringify(user));
  }
}
