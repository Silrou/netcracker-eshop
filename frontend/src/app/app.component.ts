import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  loggedIn = false;
  constructor(private router: Router) {
  }
  login(): void{
    this.loggedIn = true;
  }
  logout(): void{
    this.loggedIn = false;
    this.router.navigate(['/main']);
  }
}
