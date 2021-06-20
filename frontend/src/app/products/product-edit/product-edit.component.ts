import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ProductManagerComponent} from "../product-manager/product-manager.component";
import {ManagerWorkspaceComponent} from "../manager-workspace/manager-workspace.component";

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {
  editForm: FormGroup;
  temp: any;

  constructor(private formBuilder: FormBuilder,
              private dialog: MatDialog,
              private dialogRef: MatDialogRef<ProductEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    console.log(this.data);
    this.initForm();
  }

  private initForm(): void {
    this.editForm = this.formBuilder.group({
      productName: new FormControl(this.data.product.productName, [Validators.required]),
      productDescription: new FormControl(this.data.product.productDescription, [Validators.required]),
      productDate: new FormControl(this.data.product.productDate, [Validators.required]),
      productDiscount: new FormControl(this.data.product.productDiscount, [Validators.required]),
      productStatus: new FormControl(this.data.product.productStatus, [Validators.required]),
      productPrice: new FormControl(this.data.product.productPrice, [Validators.required]),
      productAmount: new FormControl(this.data.product.productAmount, [Validators.required]),
    });
  }

  onSubmit(): void {
    console.log(this.editForm.controls.productDate.value);
    console.log('close now&&&&&');
  }

  onClose(): void{
    console.log('cancel');
    this.dialogRef.close();
  }

}
