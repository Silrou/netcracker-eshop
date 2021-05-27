import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../../_model/user';

@Component({
  selector: 'app-personal-data-edit',
  templateUrl: './personal-data-edit.component.html',
  styleUrls: ['./personal-data-edit.component.css']
})
export class PersonalDataEditComponent implements OnInit {
  @Input()user: User;
  constructor() { }

  ngOnInit(): void {
  }

}
