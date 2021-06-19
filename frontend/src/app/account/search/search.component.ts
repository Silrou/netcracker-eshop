import { Component, OnInit } from '@angular/core';
import {Managers} from '../../_model/managers';
import {RestService} from '../../_service/rest.service';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {ProfileComponent} from '../profile/profile.component';
import {MatDialogRef} from '@angular/material/dialog';

// import {EditComponent} from "../edit/edit.component";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  // managers: Managers[] = [];
  clickedID: number;
  firstName: string;
  create: boolean;
  constructor(public rs: RestService,
              private dialog: MatDialog) {}

  ngOnInit(): void {
    this.getEmployee();
  }
  getEmployee(): void{
    this.rs.getManagers().subscribe((response: Managers[]) => {
      this.rs.managers = response;
    });
  }
  Search(): void{
    if (this.firstName === ''){
      this.ngOnInit();
    }
    else{
      this.rs.managers = this.rs.managers.filter(res => {
        return res.firstName.toLocaleLowerCase().match(this.firstName.toLocaleLowerCase());
      });
    }
  }

  onEdit(): void{
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '60%';
    this.create = false;
    this.dialog.open(ProfileComponent, dialogConfig);
  }
  onCreate(): void{
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '60%';
    this.create = true;
    this.dialog.open(ProfileComponent, dialogConfig);
  }

  onDelete(id: number): void{
   console.log(id);
   this.rs.deleteUser(id).subscribe( response => {
     this.rs.managers = this.rs.managers.filter(item => item.id !== id);
     console.log(this.rs.getManagers());
     });
   setTimeout(() => {
      this.rs.getManagers().subscribe((response: Managers[]) => {
        this.rs.managers = response;
        console.log(response);
      });
    }, 1000);
     // console.log(response);
  }
  getID(id: number): number{
    return this.clickedID = id;

  }
}
