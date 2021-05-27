import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../../_model/user';
import {UserService} from '../../../_service/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-personal-data-edit',
  templateUrl: './personal-data-edit.component.html',
  styleUrls: ['./personal-data-edit.component.css']
})
export class PersonalDataEditComponent implements OnInit {
  user = new User();
  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
  }
  save(): void{
    this.userService.sendUserInfo(this.user).subscribe(
      res => console.log('success'),
      error => console.log(error.body)
    );
    this.router.navigate(['/settings/view']);
  }

}
