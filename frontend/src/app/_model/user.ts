import {Role} from './role';

export class User{
  constructor() {
  }
  id: string;
  userName: string;
  userSurname: string;
  userLogin: string;
  userPassword: string;
  userNumber: string;
  userAddress: string;
  userRole: Role;
  status: string;
  recaptchaResponse: string;
}
