import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../_model/user";
import {OrderCard} from "../../_model/orderCard";

@Injectable({
  providedIn: 'root'
})
export class OrderHistoryService {

  constructor(private http: HttpClient) { }

  private orderHistoryUrl = 'http://localhost:8081/order-history';

  getAllOrderById(id: number): Observable<OrderCard[]> {
    const url = `${this.orderHistoryUrl}/${id}`;
    return this.http.get<OrderCard[]>(url);
  }
}
