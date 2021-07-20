import {Component, OnInit} from '@angular/core';
import {Product} from '../../_model/product';
import {ProductService} from '../../_service/product/product.service';
import {Filters} from '../../_model/filters';
import {ShoppingCartService} from '../../_service/shopping-cart/shopping-cart.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})


export class ProductListComponent implements OnInit {

  page = 1;
  size = 6;
  amountOfProducts: number;
  currentProducts: Product[] = [];
  searchValue: string;
  filtersValue: Filters;
  orderValue: string;
  isActive: boolean;


  constructor(
    private productService: ProductService,
    private shoppingCartService: ShoppingCartService
  ) {
  }

  ngOnInit(): void {
    this.shoppingCartService.productInCart = JSON.parse(localStorage.getItem('productInCart'));
    this.searchValue = '';
    this.isActive = true;
    this.filtersValue = {author: [], coverType: [], genre: [], language: [], publisher: []} as Filters;
    this.orderValue = '';
    this.getSearchedOrderedFilteredProducts();
    this.getAmountOfProducts();
  }

  onPageChange(currentPage: number): void {
    this.page = currentPage;
    this.getSearchedOrderedFilteredProducts();
  }

  getSearchedProducts(value: string): void {
    if (value !== '') {
      this.searchValue = value;
      this.getSearchedOrderedFilteredProducts();
    } else {
      this.searchValue = '';
    }
  }

  getFilteredProducts(filters: Filters): void {
    this.filtersValue = filters;
    this.getSearchedOrderedFilteredProducts();
  }

  getSearchedOrderedFilteredProducts(): void {
    this.productService.searchOrderFilterProducts(this.page, this.size, this.searchValue, this.orderValue, this.filtersValue, this.isActive)
      .subscribe(products => {
        this.currentProducts = products;
        this.checkStatus();
      });
    this.getAmountOfProducts();
  }

  cancelFilters(): void {
    window.location.reload();
  }

  getAmountOfProducts(): void {
    this.productService.getProductsCount(this.searchValue, this.orderValue, this.filtersValue, this.isActive)
      .subscribe(numb => {
        this.amountOfProducts = numb;
      });
  }

  addProductToCart(product: Product): void {
    this.shoppingCartService.addProductToCart(product);
    this.checkStatus();
  }

  checkStatus(): void {
    this.shoppingCartService.changeStatusToInCart(this.currentProducts);
  }

  getOrderedProducts(target: any): void {
    this.orderValue = target.value;
    this.getSearchedOrderedFilteredProducts();
  }

  ngOnDestroy() {

  }

}
