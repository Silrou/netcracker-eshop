import {Injectable} from '@angular/core';
import {Product} from '../../_model/product';
import {ProductToCompare} from "../../_model/productToCompare";

@Injectable({
  providedIn: 'root'
})
export class CompareService {

  public productsToCompare: Array<ProductToCompare> = [];

  constructor() {
  }

  addProductToCompare(id: number, name: string, price: number, discount: number, productPict: string,
                      categories: string[], productStatus: string): void {
    const product: ProductToCompare = {
      id: id, productName: name, productPrice: price, productDiscount: discount,
      productPict: productPict, author: categories[0], coverType: categories[1], genre: categories[2],
      language: categories[3], publisher: categories[4], productStatus: productStatus
    } as ProductToCompare;

    if (localStorage.getItem('productToCompare') !== null) {
      this.productsToCompare = JSON.parse(localStorage.getItem('productToCompare'));
      if (!(this.productsToCompare.filter(x => x.id === product.id).length > 0)) {
        this.productsToCompare.push(product);
      }
    } else {
      this.productsToCompare = [];
      this.productsToCompare.push(product);
    }
    localStorage.setItem('productToCompare', JSON.stringify(this.productsToCompare));
  }

  beingCompared(id: number): boolean {

    for (let index in this.productsToCompare) {
      if (this.productsToCompare[index].id === id) {
        return true;
      }
    }
    return false;
  }

  removeFromCompare(product: ProductToCompare) {
    const index = this.productsToCompare.indexOf(product);
    if (index > -1) {
      this.productsToCompare.splice(index, 1);
      localStorage.setItem('productToCompare', JSON.stringify(this.productsToCompare));
    }
  }

  getCompared(): ProductToCompare[] {
    return this.productsToCompare;
  }

}
