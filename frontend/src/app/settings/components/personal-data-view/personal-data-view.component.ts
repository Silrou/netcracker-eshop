import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../../_model/user';

@Component({
  selector: 'app-personal-data-view',
  templateUrl: './personal-data-view.component.html',
  styleUrls: ['./personal-data-view.component.css']
})
export class PersonalDataViewComponent implements OnInit {
  @Input()user: User;
  constructor() { }

  ngOnInit(): void {
  }

}
