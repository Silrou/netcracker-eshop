export class Managers{
  id: number;
  userName: string;
  userSurname: string;
  userLogin: string;
  userNumber: string;
  userRole: string;


  constructor(id: number, userName: string, userSurname: string, userLogin: string, userNumber: string, userRole: string) {
    this.id = id;
    this.userName = userName;
    this.userSurname = userSurname;
    this.userLogin = userLogin;
    this.userNumber = userNumber;
    this.userRole = userRole;
  }
}

