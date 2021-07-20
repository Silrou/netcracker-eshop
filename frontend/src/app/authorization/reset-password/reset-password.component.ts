import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AlertService} from '../../_service/alert/alert.service';
import {AuthService} from '../../_service/auth/auth.service';
import {first} from 'rxjs/operators';
import {ValidationMessages} from '../../_model/labels/validation.messages';
import {ErrorMessages} from '../../_model/labels/error.messages';
import {AutoUnsubscribe} from 'ngx-auto-unsubscribe';

enum TokenStatus {
  Validating,
  Valid,
  Invalid
}

@AutoUnsubscribe()
@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})

export class ResetPasswordComponent implements OnInit, OnDestroy {

  passwordErrorMessage = ValidationMessages.password;
  passwordNotMatchErrorMessage = ValidationMessages.passwordNotMatch;

  TokenStatus = TokenStatus;
  tokenStatus = TokenStatus.Validating;
  token = null;
  form: FormGroup;
  loading = false;
  submitted = false;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private authService: AuthService,
              private alertService: AlertService) { }


  ngOnInit(): void {
    this.initForm();
    const token = this.route.snapshot.queryParams.token;
    this.router.navigate([], { relativeTo: this.route, replaceUrl: true });
    this.validateToken(token);
  }

  initForm(): void {
    this.form = this.formBuilder.group({
      password: ['', [Validators.required,
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$')]],
      confirmPassword: ['', [Validators.required,
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$')]],
    }, {
      validator: this.MustMatch('password', 'confirmPassword')
    });
  }

  ngOnDestroy(): void {
  }

  MustMatch(controlName: string, matchingControlName: string): (formGroup: FormGroup) => any {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];

      if (matchingControl.errors && !matchingControl.errors.mustMatch) {
        return;
      }

      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({mustMatch: true});
      } else {
        matchingControl.setErrors(null);
      }
    };
  }

  onSubmit(): void {
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.authService.resetPassword(this.token, this.form.controls.password.value, this.form.controls.confirmPassword.value)
      .pipe(first())
      .subscribe({
        next: () => {
          this.alertService.success('Password reset successful, you can now login', { keepAfterRouteChange: true });
          this.router.navigate(['../login'], { relativeTo: this.route });
        },
        error: error => {
          this.alertService.error(ErrorMessages[error.error.message]);
          this.loading = false;
        }
      });
  }

  private validateToken(token: string): void {
    this.authService.validateResetToken(token)
      .pipe(first())
      .subscribe({
        next: () => {
          this.token = token;
          this.tokenStatus = TokenStatus.Valid;
        },
        error: () => {
          this.tokenStatus = TokenStatus.Invalid;
        }
      });
  }
}
