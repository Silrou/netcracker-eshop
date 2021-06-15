import {Component, OnInit, Pipe} from '@angular/core';
import {ProductCategory} from "../../_model/productCategory";
import {ProductCategoryService} from "../../_service/product-category/product-category.service";
import {Product} from "../../_model/product";
import {ProductService} from "../../_service/product/product.service";
import {typesOfCategories} from "../../_model/typesOfCategories";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})


export class ProductListComponent implements OnInit {


  page: number = 1;
  size: number = 50;
  allProducts: Product[] = [];
  currentProducts: Product[] = [];


  constructor(
    private productService: ProductService,
  ) {
  }

  ngOnInit(): void {
    this.getProducts();

  }

  getProducts(): void {
    this.productService.getAllProducts(this.page, this.size)
      .subscribe(products => {
        this.allProducts = products;
        this.currentProducts = this.allProducts;
      });
  }

  getSearchedProducts(value: string){
    this.productService.searchProducts(value)
      .subscribe(products => {
        console.log('inside subscribe');
        console.log(products);
        this.currentProducts = products;
      });
  }

}
