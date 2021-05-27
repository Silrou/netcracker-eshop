import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavBarComponent } from './pages/nav-bar/nav-bar.component';
import { BaseLinkComponent } from './components/base-link/base-link.component';
import { HomeLinkComponent } from './components/home-link/home-link.component';



@NgModule({
  declarations: [
    NavBarComponent,
    BaseLinkComponent,
    HomeLinkComponent
  ],
  exports: [
    NavBarComponent
  ],
  imports: [
    CommonModule
  ]
})
export class NavBarModule { }
