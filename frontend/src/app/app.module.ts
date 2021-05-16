import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { MainPageComponent } from './main-page/main-page.component';
import {AuthorizationModule} from './authorization/authorization.module';
@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
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
