import {Component, Input, OnInit} from '@angular/core';
import {Role} from '../../../_model/role';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
@Input()role: Role;
  constructor() { }

  ngOnInit(): void {
  }

}
