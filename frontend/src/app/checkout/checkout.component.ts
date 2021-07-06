import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {User} from '../_model/user';
import {CheckoutService} from '../_service/checkout/checkout.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  constructor(private checkoutService: CheckoutService) { }

  @Inject(MAT_DIALOG_DATA) public data: User;
  form: FormGroup;
  input = false;
  allComplete = false;
  workHours = [8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20];
  hours: number[] = [];
  findHours = false;
  userId: number;
  disturb = false;
  user: User = new User();

  ngOnInit(): void {
    this.findHours = false;
    this.user.userName = ' ';
    this.user.userSurname = ' ';
    this.user.userAddress = ' ';
    this.user.userNumber = ' ';
    this.userId = JSON.parse(localStorage.getItem('idUser'));
    if (this.userId !== null) {
      this.checkoutService.getUserById(this.userId).subscribe(
        res => {
          this.user = res;
          this.initForm();
        }
      );
    }
    this.initForm();
  }

  private initForm(): void {
    this.form = new FormGroup({
      firstName: new FormControl(this.user.userName, [Validators.required]),
      lastName: new FormControl(this.user.userSurname, Validators.required),
      address: new FormControl(this.user.userAddress, [Validators.required]),
      phoneNumber: new FormControl(this.user.userNumber, Validators.required),
      date: new FormControl('', Validators.required),
      deliveryTime: new FormControl('', Validators.required),
      doNotDisturb: new FormControl('', Validators.required),
      comment: new FormControl('', Validators.required)
    });
  }

  onSubmit(): void {
    if (this.form.invalid) {
      return;
    }

    const result = this.form.value;
    result.userId = this.userId;

    this.checkoutService.createOrder(result).subscribe(
      res => {

      }
    );
  }

  findDeliveryHours(value: string): void {
    const month = this.form.value.date.getUTCMonth();
    const day = this.form.value.date.getUTCDate() + 1;
    const year = this.form.value.date.getUTCFullYear();
    this.checkoutService.getDeliveryHours(new Date(year, month, day)).subscribe(
      res => {
        this.hours = this.workHours.filter(val => !res.includes(val));
        this.findHours = true;
      }
    );
  }
}
