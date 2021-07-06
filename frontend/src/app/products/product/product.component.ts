import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../../_model/product';
import {ProductService} from '../../_service/product/product.service';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {ShoppingCartService} from '../../_service/shopping-cart/shopping-cart.service';
import {CompareService} from '../../_service/compare/compare.service';
import {AutoUnsubscribe} from 'ngx-auto-unsubscribe';
import {Subscription} from "rxjs";

@AutoUnsubscribe()
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  @Input()
  product?: Product = {} as Product;

  categories?: string[] = [];

  subscriptions: Subscription[] = [];

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private location: Location,
    private shoppingCartService: ShoppingCartService,
    private compareService: CompareService
  ) {
  }

  ngOnInit(): void {
    this.getProduct();
  }

  ngOnDestroy() {
  }

  getProduct(): void {
    this.shoppingCartService.productInCart = JSON.parse(localStorage.getItem('productInCart'));
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.subscriptions.push(this.productService.getProduct(id)
      .subscribe(product => {
        this.product = product;
        this.checkStatus();
        this.getCategoriesOfProduct();
      }, error => console.log(error)));
  }

  getCategoriesOfProduct(): void {
    this.subscriptions.push(this.productService.getCategoriesOfProduct(this.product.author, this.product.coverType, this.product.genre,
      this.product.language, this.product.publisher)
      .subscribe(categories => {
        this.categories = categories;
      }));
  }

  hasDiscount(): boolean {
    if (this.product.productDiscount > 0) {
      return true;
    }
    return false;
  }

  getCategoryValue(key: number): string {
    return this.categories[key];
  }

  getDiscountedPrice(): number {
    return Math.round(this.product.productPrice * (1 - (this.product.productDiscount / 100)));
  }

  addToCart(): void {
    this.shoppingCartService.addProductToCart(this.product);
    this.checkStatus();
  }

  addToCompare(element): void{
    this.compareService.addProductToCompare(this.product.id, this.product.productName, this.product.productPrice,
      this.product.productDiscount, this.product.productPict, this.categories, this.product.productStatus);
    element.textContent = 'In compare';
  }

  checkStatus(): void {
    const array: Product[] = [this.product];
    this.shoppingCartService.changeStatusToInCart(array);
      // this.shoppingCartService.productInCart.forEach( x => {
      //   if (this.product.id === x.id) {
      //     this.product.productStatus = 'inCard';
      //   }
      // });
  }

  beingCompared(): boolean{
    if (this.compareService.beingCompared(this.product.id)){
      return true;
    }
    return false;
  }
}
