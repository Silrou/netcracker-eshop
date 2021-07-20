import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor() {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const tokenRequest = (document.cookie.split(';').find(x => x.includes('Token')) || '=').split('=')[1];
    request = request.clone({
      setHeaders: {Authorization: `${tokenRequest}`}
    });
    return next.handle(request).pipe(
      tap(
        event => {
          if (event instanceof HttpResponse) {
            const tokenResponse = event.headers.get('Authorization');
            if (tokenResponse !== null) {
              const expires = new Date(Date.now() + 4 * 60 * 60 * 1000).toUTCString();
              document.cookie = `Token=${tokenResponse}; expires=${expires}; path=/`;
            }
          }
        }
      )
    );
  }
}
