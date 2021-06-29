export class ErzacModel{
  courierId: string;
  cartid: number;
  date: string;


  constructor(courierId: string, cartid: number, date: string) {
    this.courierId = courierId;
    this.cartid = cartid;
    this.date = date;
  }
}
