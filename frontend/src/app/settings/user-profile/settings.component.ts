import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from '../../_service/auth/auth.service';
import {SettingsService} from '../../_service/settings/settings.service';
import {User} from '../../_model/user';
import {AutoUnsubscribe} from 'ngx-auto-unsubscribe';

@AutoUnsubscribe()
@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit, OnDestroy {

  constructor(private settingsService: SettingsService,
              public authService: AuthService) { }

  user: User;

  ngOnInit(): void {
    const login = localStorage.getItem('login');

    this.settingsService.getUserByLogin(login).subscribe(
      res => {
        this.user = res;
      }
    );
  }

  ngOnDestroy(): void {
  }

}
