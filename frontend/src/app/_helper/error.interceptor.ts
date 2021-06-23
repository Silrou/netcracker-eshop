import {AuthService} from '../_service/auth.service';
import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {AlertService} from '../_service/alert.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService,
              private  alertService: AlertService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      if ([401, 403].includes(err.status)) {
        if (err.url.includes('settings')){
          this.alertService.warn(`
                        <h4>You change email.</h4>
                        <p>Please verify new email in your mail box</p>
                    `, { autoClose: false, keepAfterRouteChange: true });
        }
        console.log('now is log out this app');
        this.authService.logout();
        }



      const error = (err && err.error && err.error.message) || err.statusText;
      console.error(err);
      return throwError(error);
    }));
  }
}
