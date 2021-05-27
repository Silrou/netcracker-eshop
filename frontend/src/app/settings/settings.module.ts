import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SettingsComponent } from './pages/settings/settings.component';
import { PersonalDataViewComponent } from './components/personal-data-view/personal-data-view.component';
import { PersonalDataEditComponent } from './components/personal-data-edit/personal-data-edit.component';



@NgModule({
  declarations: [
    SettingsComponent,
    PersonalDataViewComponent,
    PersonalDataEditComponent
  ],
  imports: [
    CommonModule
  ]
})
export class SettingsModule { }
