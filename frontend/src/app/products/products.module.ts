import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductComponent } from './product/product.component';
import { ProductListComponent } from './product-list/product-list.component';
import { AppRoutingModule } from '../app-routing.module';
import { ProductCardComponent } from './product-card/product-card.component';
import { ProductSearchComponent } from './product-search/product-search.component';
import { CategoriesPartComponent } from './categories-part/categories-part.component';
import { ProductManagerComponent } from './product-manager/product-manager.component';
import {ManagerWorkspaceComponent} from './manager-workspace/manager-workspace.component';
import {MatIconModule} from '@angular/material/icon';
import { ProductEditComponent as ProductEditComponent } from './product-edit/product-edit.component';
import {ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatOptionModule} from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';
import {MatRadioModule} from '@angular/material/radio';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    ProductComponent,
    ProductListComponent,
    ProductCardComponent,
    ProductSearchComponent,
    CategoriesPartComponent,
    ProductManagerComponent,
    ManagerWorkspaceComponent,
    ProductEditComponent,
  ],
    imports: [
        CommonModule,
        AppRoutingModule,
        MatIconModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatOptionModule,
        MatSelectModule,
        MatDialogModule,
        MatRadioModule,
        BrowserAnimationsModule
    ],
  providers: [
    CategoriesPartComponent
  ]
})
export class ProductsModule { }
