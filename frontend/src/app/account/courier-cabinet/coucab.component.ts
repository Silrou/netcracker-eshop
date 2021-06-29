import {Component, Input, OnInit} from '@angular/core';
import {RestService} from '../../_service/rest.service';
import {CourierPackages} from '../../_model/CourierPackages';
import {ErzacModel} from '../../_model/erzacModel';

class Couriercabinets {
}

@Component({
  selector: 'app-couriercabinet',
  templateUrl: './coucab.component.html',
  styleUrls: ['./coucab.component.css']
})

export class CoucabComponent implements OnInit {

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
  erzacModel: ErzacModel;

  constructor(public rs: RestService,
              // private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.rs.getTask(localStorage.getItem('idUser')).subscribe((response) => {
      this.courierpackages = response;
    });
  }

  // getByCourierID(id: number) {
  //     return this.user.id = id;
  //  }
  setNewStatus(ids: number): void {
    this.rs.setStatus(ids).subscribe((response) => {
      this.courierpackages = response;
    });
  }
}
