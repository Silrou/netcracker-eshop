import {Component, Input, OnInit,  ViewChild,
  ViewContainerRef,
  ComponentFactoryResolver,
  ComponentRef,
  ComponentFactory} from '@angular/core';
import {Managers} from '../../_model/managers';
import {RestService} from '../../_service/rest.service';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {ProfileComponent} from '../profile/profile.component';
import {MatDialogRef} from '@angular/material/dialog';
import {User} from "../../_model/user";




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
  constructor(public rs: RestService,
              private dialog: MatDialog) {}

  ngOnInit(): void {
    this.getEmployee();
  }
  getEmployee(): void{
    this.rs.getManagers().subscribe((response: User[]) => {
      this.rs.users = response;
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

  onEdit(manager: Managers): void{
    // this.edit = true;
    // this.profile.onEdit(manager);
  }
  onCreate(): void{
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '60%';
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

  getID(id: number): number {
    return this.clickedID = id;
  }

  getAllManager(): void {
    this.rs.getManager().subscribe((response) => {
      this.rs.managers = response;
    });
  }

  getAllCoriers(): void {
    this.rs.getCorier().subscribe((response) => {
      this.rs.managers = response;
    });
  }

  getOnDuty(): void {
    this.rs.getOnDutyNow().subscribe((response) => {
      this.rs.managers = response;
    });
  }
}
