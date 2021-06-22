import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, map, tap} from 'rxjs/operators';
import {Observable, of} from 'rxjs';
import {Product} from '../../_model/product';

import {PRODUCTS} from '../../_utils/products'; // удалить, когда продукты будут браться с бэка

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(
    private http: HttpClient,
  ) {
  }

  private productsUrl = 'http://localhost:8081/product';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  getProductsCount(): Observable<number> {
    const url = `${this.productsUrl}/count`;
    return this.http.get<number>(url);
  }

  getAllProducts(page: number, size: number) {
    const url = `${this.productsUrl}/get-all?page=${page}&size=${size}`;
    return this.http.get<Product[]>(url)
      .pipe(
        catchError(this.handleError<Product[]>('getAllProducts', []))
      );
  }

  getProduct(id: number): Observable<Product> {
    const url = `${this.productsUrl}/get-by-id/${id}`;
    return this.http.get<Product>(url)
      .pipe(
        catchError(this.handleError<Product>(`getProduct id=${id}`))
      );
  }

  updateProduct(product: Product): Observable<any> {
    const url = `${this.productsUrl}/update`;
    return this.http.put(url, product);
  }

  addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.productsUrl, product, this.httpOptions)
      .pipe(
        catchError(this.handleError<Product>('addProduct'))
      );
  }

  deleteProduct(id: number): Observable<Product> {
    const url = `${this.productsUrl}/${id}`;
    return this.http.delete<Product>(url, this.httpOptions)
      .pipe(
        catchError(this.handleError<Product>('deleteProduct'))
      );
  }

  searchProducts(term: string): Observable<Product[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<Product[]>(`${this.productsUrl}/get-by-name/${term}`)
      .pipe(
        catchError(this.handleError<Product[]>('getProductsByName', []))
      );
  }
}
