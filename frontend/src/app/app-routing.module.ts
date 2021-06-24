import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {MainPageComponent} from './main-page/main-page.component';
import {LoginComponent} from './authorization/login/login.component';
import {RegistrationComponent} from './authorization/registration/registration.component';
import {ShoppingCartComponent} from './shopping-cart/shopping-cart.component';
import {SettingsComponent} from './settings/settings.component';

import {AuctionComponent} from './auctions/auction/auction.component';
import {AuctionListComponent} from './auctions/auction-list/auction-list.component';
import {BidComponent} from './auctions/bid/bid.component';

import {ProductComponent} from './products/product/product.component';
import {ProductListComponent} from './products/product-list/product-list.component';
// import {SettingsComponent} from './settings/pages/settings/settings.component';
// import {PersonalDataViewComponent} from './settings/components/personal-data-view/personal-data-view.component';
// import {PersonalDataEditComponent} from './settings/components/personal-data-edit/personal-data-edit.component';
// import {ProfileComponent} from './account/profile/profile.component';
// @ts-ignore
import {SearchComponent} from './account/search/search.component';
import {ManagerWorkspaceComponent} from './products/manager-workspace/manager-workspace.component';
import {ProductEditComponent} from './products/product-edit/product-edit.component';
import {ForgotPasswordComponent} from './authorization/forgot-password/forgot-password.component';
import {EditSettingsComponent} from './settings/edit/edit-settings/edit-settings.component';
import {Role} from './_model/role';
import {AuthGuard} from './_helper/auth.guard';
import {VerifyEmailComponent} from './authorization/mail/verify-email/verify-email.component';
import {OrderDetailsComponent} from "./settings/order-history/order-details/order-details.component";
import {ResetPasswordComponent} from "./authorization/reset-password/reset-password.component";

// const settingsChildRoutes: Routes = [
//   { path: 'view', component: PersonalDataViewComponent},
//   { path: 'edit', component: PersonalDataEditComponent},
// ];

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
    path: 'auction-list',
    component: AuctionListComponent
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
    path: 'product-list',
    component: ProductListComponent
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
    component: SettingsComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.USER, Role.MANAGER, Role.ADMIN, Role.COURIER] }
  },
  // {
  //   path: 'settings',
  //   component: SettingsComponent,
  //   children: settingsChildRoutes
  // },
  {
    path: 'search',
    component: SearchComponent
  },
  {
    path: 'temp',
    component: ProductEditComponent
  },
  {
    path: 'manager',
    component: ManagerWorkspaceComponent
  },
  {
    path: 'forgot-password',
    component: ForgotPasswordComponent
  },
  {
    path: 'verify-email',
    component: VerifyEmailComponent
  },
  {
    path: 'reset-password',
    component: ResetPasswordComponent
  },
  {
    path: 'settings/edit',
    component: EditSettingsComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.USER] }
  },
  {
    path: 'settings/order-details',
    component: OrderDetailsComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.USER] }
  },
  // {
  //   path: 'account/profile',
  //   component: ProfileComponent
  // }
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
