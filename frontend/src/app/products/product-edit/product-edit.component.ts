import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {
  form: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private dialog: MatDialog,
              private dialogRef: MatDialogRef<ProductEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    console.log(this.data);
    this.initForm();
  }

  private initForm(): void {
    // this.form = this.formBuilder.group({
    //   productName: new FormControl(this.product.name, [Validators.required]),
    //   description: new FormControl(this.product.description),
    // })
  }

  onSubmit(): void {

  }

  onClose(): void{
    this.dialogRef.close();
  }

  Submit(): void {

  }
}
