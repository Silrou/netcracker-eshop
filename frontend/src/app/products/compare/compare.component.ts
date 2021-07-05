import {Component, Input, OnInit} from '@angular/core';
import {CompareService} from '../../_service/compare/compare.service';
import {Product} from "../../_model/product";
import {ProductToCompare} from "../../_model/productToCompare";
import {element} from "protractor";
import {CdkTableModule} from '@angular/cdk/table';

@Component({
  selector: 'app-compare',
  templateUrl: './compare.component.html',
  styleUrls: ['./compare.component.css']
})
export class CompareComponent implements OnInit {

  products: ProductToCompare[];
  columns = [];

  constructor(private compareService: CompareService) {
  }

  ngOnInit(): void {
    this.products = this.compareService.getCompared();
  }

  hasDiscount(product: ProductToCompare): boolean {
    if (product.productDiscount > 0) {
      return true;
    }
    return false;
  }

  getDiscountedPrice(product: ProductToCompare): number {
    return Math.round(product.productPrice * (1 - (product.productDiscount / 100)));
  }

  remove(product: ProductToCompare){
    this.compareService.removeFromCompare(product);
    this.products = this.compareService.getCompared();
  }

}
