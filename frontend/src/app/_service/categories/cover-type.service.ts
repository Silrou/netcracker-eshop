import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {CoverType} from '../../_model/cover-type';
import {Author} from '../../_model/author';

@Injectable({
  providedIn: 'root'
})
export class CoverTypeService {

  constructor(private http: HttpClient) { }

  private coverTypeUrl = 'http://localhost:8081/cover-type';


  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

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
      );
  }

  getCoverTypes(): CoverType[] {
    if (localStorage.getItem('coverTypes') === null) {
      this.getAllCoverTypes().subscribe(
        res => {
          localStorage.setItem('coverTypes', JSON.stringify(res));
        }
      );
    }
    return JSON.parse(localStorage.getItem('coverTypes'));
  }
}
