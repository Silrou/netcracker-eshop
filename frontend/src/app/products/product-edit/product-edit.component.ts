import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthorService} from '../../_service/categories/author.service';
import {Author} from '../../_model/author';
import {CoverType} from '../../_model/cover-type';
import {Genre} from '../../_model/genre';
import {CoverTypeService} from '../../_service/categories/cover-type.service';
import {GenreService} from '../../_service/categories/genre.service';
import {LanguageService} from '../../_service/categories/language.service';
import {PublisherService} from '../../_service/categories/publisher.service';
import {Language} from '../../_model/Language';
import {Publisher} from '../../_model/Publisher';
import {Product} from '../../_model/product';
import {ProductService} from '../../_service/product/product.service';
import {ValidationMessages} from '../../_model/labels/validation.messages';
import {Subscription} from 'rxjs';
import {AutoUnsubscribe} from 'ngx-auto-unsubscribe';

@AutoUnsubscribe()
@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit, OnDestroy {

  nameErrorMessage = ValidationMessages.productName;
  descriptionErrorMessage = ValidationMessages.productDescription;
  discountErrorMessage = ValidationMessages.productDiscount;
  priceErrorMessage = ValidationMessages.productPrice;
  amountErrorMessage = ValidationMessages.productAmount;
  authorErrorMessage = ValidationMessages.productAuthor;
  publisherErrorMessage = ValidationMessages.productPublisher;
  genreErrorMessage = ValidationMessages.productGenre;
  coverTypeErrorMessage = ValidationMessages.productCoverType;
  languageErrorMessage = ValidationMessages.productLanguage;
  pictureErrorMessage = ValidationMessages.productProductPicture;

  editForm: FormGroup;
  authors: Author[] = [];
  genres: Genre[] = [];
  coverTypes: CoverType[] = [];
  languages: Language[] = [];
  publishers: Publisher[] = [];
  result: Product;
  formClose = false;
  subscriptions: Subscription[] = [];

  constructor(private formBuilder: FormBuilder,
              private dialog: MatDialog,
              private dialogRef: MatDialogRef<ProductEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private authorService: AuthorService,
              private coverTypeService: CoverTypeService,
              private genreService: GenreService,
              private languageService: LanguageService,
              private publisherService: PublisherService,
              private productService: ProductService) {
  }

  ngOnInit(): void {
    this.genres = this.genreService.getGenres();
    this.coverTypes = this.coverTypeService.getCoverTypes();
    this.authors = this.authorService.getAuthors();
    this.languages = this.languageService.getLanguages();
    this.publishers = this.publisherService.getPublishers();
    this.initForm();
  }

  ngOnDestroy(): void {
  }

  private initForm(): void {
    this.editForm = this.formBuilder.group({
      productName: new FormControl(this.data.product.productName, [Validators.required,
        Validators.maxLength(28),
        Validators.minLength(1)]),
      productDescription: new FormControl(this.data.product.productDescription, [Validators.required,
        Validators.maxLength(100),
        Validators.minLength(1)]),
      productDate: new FormControl(this.data.product.productDate, [Validators.required]),
      productDiscount: new FormControl(this.data.product.productDiscount, [Validators.required,
        Validators.pattern('^0*(?:[0-9][0-9]?|99)$')]),
      productStatus: new FormControl(this.data.product.productStatus, [Validators.required]),
      productPrice: new FormControl(this.data.product.productPrice, [Validators.required,
        Validators.pattern('^[1-9][0-9]*$')]),
      productAmount: new FormControl(this.data.product.productAmount, [Validators.required,
        Validators.pattern('^[0-9][0-9]*$')]),
      author: new FormControl(this.data.product.author, [Validators.required, Validators.min(0)]),
      publisher: new FormControl(this.data.product.publisher, [Validators.required, Validators.min(0)]),
      genre: new FormControl(this.data.product.genre, [Validators.required, Validators.min(0)]),
      coverType: new FormControl(this.data.product.coverType, [Validators.required, Validators.min(0)]),
      language: new FormControl(this.data.product.language, [Validators.required, Validators.min(0)]),
      productPict: new FormControl(this.data.product.productPict, [Validators.required,
        Validators.maxLength(100),
        Validators.minLength(1)]),
    });
  }


  onClose(): void {
    this.formClose = true;
    this.dialogRef.close('close');
  }

  onSubmit(): void {
    if (this.editForm.invalid || this.formClose) {
      return;
    }
    const result = this.editForm.value;
    result.id = this.data.product.id;
    console.log(result);

    this.subscriptions.push(this.productService.updateProduct(result).subscribe(
      res => {
      }
    ));
    this.onClose();
  }

}
