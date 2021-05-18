import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {MainPageComponent} from './main-page/main-page.component';
import {LoginComponent} from './authorization/login/login.component';
import {RegistrationComponent} from './authorization/registration/registration.component';
import {AuctionsComponent} from './auctions/auctions.component';
import {ProductsComponent} from './products/products.component';
import {ShoppingCartComponent} from './shopping-cart/shopping-cart.component';
import {SettingsComponent} from './settings/settings.component';
import {ProductComponent} from './product/product.component';
import {AuctionComponent} from './auction/auction.component';
import {BidComponent} from './bid/bid.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'main',
    pathMatch: 'full'
  },
  {
    path: 'main',
    component: MainPageComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegistrationComponent
  },
  {
    path: 'auctions',
    component: AuctionsComponent
  },
  {
    path: 'auction',
    component: AuctionComponent
  },
  {
    path: 'bid',
    component: BidComponent
  },
  {
    path: 'products',
    component: ProductsComponent
  },
  {
    path: 'product',
    component: ProductComponent
  },
  {
    path: 'shopping-cart',
    component: ShoppingCartComponent
  },
  {
    path: 'settings',
    component: SettingsComponent
  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
