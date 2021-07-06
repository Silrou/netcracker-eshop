import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output} from '@angular/core';
import {Product} from '../../_model/product';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AutoUnsubscribe} from 'ngx-auto-unsubscribe';

@AutoUnsubscribe()
@Component({
  selector: 'app-product-in-cart',
  templateUrl: './product-in-cart.component.html',
  styleUrls: ['./product-in-cart.component.css']
})
export class ProductInCartComponent implements OnInit, OnChanges, OnDestroy {

  @Input() product: Product;
  @Input() productsWithErrors: Product[];
  @Input() countError: boolean;
  @Input() myInput = true;
  @Output() updateAmount = new EventEmitter<string>();
  @Output() remove = new EventEmitter<Product>();

  amount: number;
  form: FormGroup;
  priceWithDiscount: number;
  countErrorMessage = 'Count must be integer and greater than 0';
  stockErrorMessage: string;
  storeAmountProblem: boolean;
  productStatusError: boolean;

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.stockErrorMessage = 'Not enough product in stock, please remove productCount products';
    this.storeAmountProblem = false;
    this.amount = this.product.productAmount;
    this.initForm();
    this.priceWithDiscount = Math.round(this.product.productPrice * (1 - (this.product.productDiscount / 100)) * this.amount);
    this.checkAmount();
    this.productStatusError = false;
  }

  private initForm(): void {
    this.form = this.formBuilder.group({
      productAmount: new FormControl(this.amount, [Validators.required,
        Validators.pattern('^[1-9][0-9]*$')])
    });
  }

  onChange($event: Event): void {
    this.amount = this.form.value.productAmount;
    this.priceWithDiscount = Math.round(this.product.productPrice * (1 - (this.product.productDiscount / 100)) * this.amount);
    this.product.productAmount = this.amount;
    this.updateAmount.emit('updatePrice');
    this.storeAmountProblem = false;
  }

  removeProduct(): void {
    this.remove.emit(this.product);
    this.storeAmountProblem = false;
    this.productStatusError = false;
  }

  ngOnChanges(): void {
    this.ngOnInit();
  }

  checkAmount(): void {
    if (this.productsWithErrors) {
      const num = this.productsWithErrors.find(x => x.id === this.product.id);
      if (num !== undefined) {
        if (num.productStatus !== 'ACTIVE') {
          this.productStatusError = true;
        }
        if (num.productAmount >= 0) {
          this.storeAmountProblem = true;
          this.stockErrorMessage = this.stockErrorMessage.replace('productCount', String(num.productAmount));
        }
      }
    }
  }

  ngOnDestroy(): void {
  }

}
