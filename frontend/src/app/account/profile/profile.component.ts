import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';
import {Managers} from '../../_model/managers';
import {RestService} from '../../_service/rest.service';
import {HttpClient} from '@angular/common/http';
import { Model, SurveyNG, JsonObject } from 'survey-angular';
import {SearchComponent} from '../search/search.component';

const surveyJSON = {};
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  constructor(
    private formBuilder: FormBuilder,
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<ProfileComponent>,
    private service: RestService,
    private http: HttpClient
    ){}
  form: FormGroup;
  emailRegx = /^(([^<>+()\[\]\\.,;:\s@"-#$%&=]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,3}))$/;
  // managers: Managers[] = [];
  newPerson = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
    phoneNumber: new FormControl(''),
    role: new FormControl('')
  });


  ngOnInit(): void {
    this.service.getManagers();
    this.form = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      role: ['', Validators.required]
     });
  }

  // submit(): void {
  //
  //   if (!this.form.valid) {
  //     return;
  //   }
  //   console.log(this.form.value);
  // }

  Submit(): void{
    const controls = this.form.controls;

    /** Проверяем форму на валидность */
    if (this.form.invalid) {
      Object.keys(controls)
        .forEach(controlName => controls[controlName].markAsTouched());

      return;
    }

    /** TODO: Обработка данных формы */
    console.log(this.form.value);
    this.service.addMember(this.form.value).subscribe((result) => {
      console.warn(result);
      localStorage.setItem('Users', JSON.stringify(this.form.value));
    });
    console.log('Start get all');
    setTimeout(() => {
      this.service.getManagers().subscribe((response: Managers[]) => {
      this.service.managers = response;
      console.log(response);
      });
      }, 1000);
    console.log('End get all');

    this.onClose();
  }
onClose(): void{
    this.dialogRef.close();
  }
getAllEmployees(): void{
    this.service.getManagers().subscribe((response: Managers[]) => {
      this.service.managers = response;
      console.log(this.service.managers);
    });
  }
  }





