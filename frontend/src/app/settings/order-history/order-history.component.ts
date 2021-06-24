import {Component, Input, OnInit} from '@angular/core';
import {SettingsService} from '../../_service/settings/settings.service';
import {OrderCard} from '../../_model/orderCard';
import {OrderHistoryService} from '../../_service/order-history/order-history.service';
import {Product} from '../../_model/product';
import {User} from '../../_model/user';
import {Router} from '@angular/router';
import {OrderDetailsService} from '../../_service/order-history/order-details.service';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {

  constructor(private orderHistoryService: OrderHistoryService,
              private router: Router,
              private orderDetailsService: OrderDetailsService) { }

  @Input()
  user?: User;
  orderCards: OrderCard[] = [];

  ngOnInit(): void {
    this.orderHistoryService.getAllOrderById(this.user.id).subscribe(
      res => {
        console.log(res);
        this.orderCards = res;
      }
    );
  }

  viewDetails(id: number): void {
    this.orderDetailsService.setOrderId(id);
    this.router.navigate(['/settings/order-details']);
  }
}
