import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../_model/user';

@Injectable({
  providedIn: 'root'
})

export class CheckoutService {

  constructor(private http: HttpClient) {
  }

  private checkoutUrl = 'http://localhost:8081/checkout';

  getDeliveryHours(date: Date): Observable<any> {
    const params = new HttpParams().set('date', JSON.stringify(date));
    const url = `${this.checkoutUrl}/hours`;
    return this.http.get<any>(url, {params});
  }

  createOrder(result: any): Observable<any> {
    const url = `${this.checkoutUrl}/order`;
    return this.http.post<any>(url, result);
  }

  getUserById(id: number): Observable<User> {
    const url = `${this.checkoutUrl}/user-info/${id}`;
    return this.http.get<User>(url);
  }
}
