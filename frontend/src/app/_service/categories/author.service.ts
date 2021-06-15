import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Genre} from "../../_model/genre";
import {catchError} from "rxjs/operators";
import {Author} from "../../_model/author";

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  private authorUrl = 'http://localhost:8081/author';

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

  getAllAuthors(): Observable<Author[]> {
    const url = `${this.authorUrl}/get-all`;
    return this.http.get<Author[]>(url)
      .pipe(
        catchError(this.handleError<Author[]>('getAllAuthors', []))
      );
  }

  getAuthor(id: number): Observable<Author> {
    const url = `${this.authorUrl}/get-by-id/${id}`;
    return this.http.get<Author>(url)
      .pipe(
        catchError(this.handleError<Author>(`getAuthor id=${id}`))
      )
  }
}
