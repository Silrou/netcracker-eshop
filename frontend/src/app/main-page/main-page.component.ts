import {Component, OnInit} from '@angular/core';
import {AuthService} from '../_service/auth.service';
import {Product} from "../_model/product";
import {ProductService} from "../_service/product/product.service";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  newProducts: Product[] = [];

  popularProducts: Product[] = [];
  page = 1;
  size = 4;

  constructor(
    private productService: ProductService) {
  }

  ngOnInit(): void {
    this.getNewProducts();
    this.getPopularProducts();
  }

  getNewProducts(): void {
    this.productService.getNewProducts(this.page, this.size)
      .subscribe(products => {
        this.newProducts = products;
      });
  }

  getPopularProducts(): void {
    this.productService.getPopularProducts(this.page, this.size)
      .subscribe(products => {
        this.popularProducts = products;
      });
  }


}
