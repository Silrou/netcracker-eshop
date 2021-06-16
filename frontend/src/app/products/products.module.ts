import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductComponent } from './product/product.component';
import { ProductListComponent } from './product-list/product-list.component';
import { AppRoutingModule } from '../app-routing.module';
import { ProductCardComponent } from './product-card/product-card.component';
<<<<<<< HEAD
=======
import { ProductSearchComponent } from './product-search/product-search.component';
import { CategoriesPartComponent } from './categories-part/categories-part.component';
>>>>>>> develop


@NgModule({
  declarations: [
    ProductComponent,
    ProductListComponent,
<<<<<<< HEAD
    ProductCardComponent
=======
    ProductCardComponent,
    ProductSearchComponent,
    CategoriesPartComponent
>>>>>>> develop
  ],
  imports: [
    CommonModule,
    AppRoutingModule
  ]
})
export class ProductsModule { }
