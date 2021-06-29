import {Component, Input, OnInit} from '@angular/core';
import {RestService} from '../../_service/rest.service';
import {CourierPackages} from '../../_model/CourierPackages';
import {User} from '../../_model/user';


class Couriercabinets {
}

@Component({
  selector: 'app-couriercabinet',
  templateUrl: './coucab.component.html',
  styleUrls: ['./coucab.component.css']
})

export class CoucabComponent implements OnInit {
  @Input()
  user?: User;
  courierpackages: CourierPackages[] = [];
  hour: number;
  id: number;
  username: string;
  dontdisturb: boolean;
  fulladdress: string;
  deliverytime: string;
  totalprice: number;
  orderstatus: string;
  packagedescription: string;

  constructor(public rs: RestService,
              // private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    console.log(this.user.id);
    this.rs.getTask().subscribe((response) => {
      this.courierpackages = response;
    });
  }

  getByCourierID(id: number) {
      return this.user.id = id;
   }
}
