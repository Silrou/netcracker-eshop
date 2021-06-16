import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {Publisher} from "../../_model/Publisher";

@Injectable({
  providedIn: 'root'
})
export class PublisherService {

  private publisherUrl = 'http://localhost:8081/publisher';

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) { }

  getAllPublishers(): Observable<Publisher[]> {
    const url = `${this.publisherUrl}/get-all`;
    return this.http.get<Publisher[]>(url)
      .pipe(
        catchError(this.handleError<Publisher[]>('getAllPublishers', []))
      );
  }

  getPublisher(id: number): Observable<Publisher> {
    const url = `${this.publisherUrl}/get-by-id/${id}`;
    return this.http.get<Publisher>(url)
      .pipe(
        catchError(this.handleError<Publisher>(`getPublisher id=${id}`))
      )
  }

}
