import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, map, tap} from 'rxjs/operators';
import {Observable, of} from "rxjs";
import {Product} from "../../_model/product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productsUrl = 'localhost:8081/api/products';

  private handleError<T>(operation = 'operation', result?: T){
    return (error: any): Observable<T> => {
      console.log(`${operation} failed: ${error.message}`);
      return of (result as T);
    };
  }

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  getAllProducts(): Observable<Product[]>{
    return this.http.get<Product[]>(this.productsUrl)
      .pipe(
        catchError(this.handleError<Product[]>('getAllProducts', []))
      );
  }

  getProduct(id: number): Observable<Product> {
    const url = `${this.productsUrl}/${id}`;
    return this.http.get<Product>(url)
      .pipe(
        catchError(this.handleError<Product>(`getProduct id=${id}`))
      )
  }

  updateProduct(product: Product): Observable<any> {
    return this.http.put(this.productsUrl, product, this.httpOptions)
      .pipe(
        catchError(this.handleError<any>('updateProduct'))
      );
  }

  addProduct(product: Product): Observable<Product>{
    return this.http.post<Product>(this.productsUrl, product, this.httpOptions)
      .pipe(
        catchError(this.handleError<Product>('addProduct'))
      );
  }

  deleteProduct(id: number): Observable<Product>{
    const url = `${this.productsUrl}/${id}`;
    return this.http.delete<Product>(url, this.httpOptions)
      .pipe(
        catchError(this.handleError<Product>('deleteProduct'))
      );
  }

  searchProducts(term: string): Observable<Product[]>{
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<Product[]>(`${this.productsUrl}/?name=${term}`)
      .pipe(
        catchError(this.handleError<Product[]>('searchHeroes', []))
      );
  }

  constructor(
    private http: HttpClient,
  ) { }
}
