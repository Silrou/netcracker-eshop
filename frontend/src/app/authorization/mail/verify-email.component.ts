import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../_service/auth/auth.service';
import {AlertService} from '../../_service/alert/alert.service';
import {AutoUnsubscribe} from 'ngx-auto-unsubscribe';

enum EmailStatus {
  Verifying,
  Failed
}

@AutoUnsubscribe()
@Component({
  selector: 'app-verify-email',
  templateUrl: './verify-email.component.html',
  styleUrls: ['./verify-email.component.css']
})
export class VerifyEmailComponent implements OnInit, OnDestroy {
  EmailStatus = EmailStatus;
  emailStatus = EmailStatus.Verifying;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private authService: AuthService,
              private alertService: AlertService) {
  }

  ngOnInit(): void {
    const token = this.route.snapshot.queryParams.token;
    this.router.navigate([], {relativeTo: this.route, replaceUrl: true});

    this.authService.verifyEmail(token)
      .subscribe({
        next: () => {
          this.alertService.success('Verification successful, you can now login', {keepAfterRouteChange: true});
          this.router.navigate(['/login']);
        },
        error: () => {
          this.emailStatus = EmailStatus.Failed;
        }
      });
  }

  ngOnDestroy(): void {
  }

}
