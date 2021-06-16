import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {CoverType} from "../../_model/cover-type";

@Injectable({
  providedIn: 'root'
})
export class CoverTypeService {

  private coverTypeUrl = 'http://localhost:8081/cover-type';

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

  getAllCoverTypes(): Observable<CoverType[]> {
    const url = `${this.coverTypeUrl}/get-all`;
    return this.http.get<CoverType[]>(url)
      .pipe(
        catchError(this.handleError<CoverType[]>('getAllCoverTypes', []))
      );
  }

  getCoverType(id: number): Observable<CoverType> {
    const url = `${this.coverTypeUrl}/get-by-id/${id}`;
    return this.http.get<CoverType>(url)
      .pipe(
        catchError(this.handleError<CoverType>(`getCoverType id=${id}`))
      )
  }
}
