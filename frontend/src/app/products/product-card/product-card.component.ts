import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from '../../_model/product';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {

  @Input()
  product?: Product;
  @Output() addProductToCart = new EventEmitter<Product>();

  constructor() { }

  ngOnInit(): void {
  }

  del(): void {
    localStorage.removeItem('productInCart');
  }

  addToCart(): void {
    this.addProductToCart.emit(this.product);
  }

  hasDiscount(): boolean {
    if (this.product.productDiscount > 0) {
      return true;
    }
    return false;
  }

  getDiscountedPrice(): number{
    return Math.round(this.product.productPrice * ( 1 - (this.product.productDiscount / 100) ));
  }

}
