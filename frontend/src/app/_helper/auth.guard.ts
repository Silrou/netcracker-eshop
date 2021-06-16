import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import {AuthService} from '../_service/auth.service';
import {AlertService} from '../_service/alert.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private authService: AuthService,
    private alertService: AlertService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const status = this.authService.status;
    if (status === 'AUTHORIZED') {
      // check if route is restricted by role
      if (route.data.roles && !route.data.roles.includes(this.authService.role)) {
        // role not authorized so redirect to home page
        this.alertService.error('<h4>Access denied.</h4> <p> Access is denied. Insufficient rights to access this page</p>',
          { autoClose: false, keepAfterRouteChange: true });
        this.router.navigate(['/']);
        return false;
      }

      // authorized so return true
      return true;
    }


    this.alertService.error('<h4>Access denied. You are not authorized</h4> <p> Please login first</p>',
      { autoClose: false, keepAfterRouteChange: true });

    // not logged in so redirect to login page with the return url
    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
    return false;
  }


}
