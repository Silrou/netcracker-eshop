import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { MainPageComponent } from './main-page/main-page.component';
import {AuthorizationModule} from './authorization/authorization.module';
import { ProductsComponent } from './products/products.component';
import { AuctionsComponent } from './auctions/auctions.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { SettingsComponent } from './settings/settings.component';
import { ProductComponent } from './product/product.component';
import { AuctionComponent } from './auction/auction.component';
import { BidComponent } from './bid/bid.component';

@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    ProductsComponent,
    AuctionsComponent,
    ShoppingCartComponent,
    SettingsComponent,
    ProductComponent,
    AuctionComponent,
    BidComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthorizationModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
