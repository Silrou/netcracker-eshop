import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable, of, throwError} from 'rxjs';
import {Product} from '../../_model/product';
import {catchError, first} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

  constructor(private http: HttpClient) { }

  private productsUrl = 'http://localhost:8081/shopping-cart';

  getOrderConfirm(products: Array<Product>): Observable<any> {
    let params = new HttpParams();
    params = params.append('products', JSON.stringify(products));
    const url = `${this.productsUrl}/check-product-in-stock`;
    return this.http.get<any>(url, {params});
  }

}
