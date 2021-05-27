import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../../_model/user';
import {UserService} from '../../../_service/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-personal-data-view',
  templateUrl: './personal-data-view.component.html',
  styleUrls: ['./personal-data-view.component.css']
})
export class PersonalDataViewComponent implements OnInit {
  user = new User();
  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
    this.userService.getUserInfo().subscribe(
      res => {
        this.user.firstName = res.firstName;
        this.user.lastName = res.lastName;
        this.user.email = res.email;
        this.user.password = res.password;
      }
    );
  }
  edit(): void{
    this.router.navigate(['/settings/edit']);
  }

}
