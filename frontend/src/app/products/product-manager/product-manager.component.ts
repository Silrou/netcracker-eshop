import {Component, Input, OnInit} from '@angular/core';
import {CategoriesPartComponent} from '../categories-part/categories-part.component';
import {ProductService} from '../../_service/product/product.service';
import {Product} from '../../_model/product';
import {Author} from '../../_model/author';
import {AuthorService} from '../../_service/categories/author.service';
import {Language} from "../../_model/Language";
import {CoverType} from "../../_model/cover-type";
import {Genre} from "../../_model/genre";
import {Publisher} from "../../_model/Publisher";

@Component({
  selector: 'app-product-manager',
  templateUrl: './product-manager.component.html',
  styleUrls: ['./product-manager.component.css']
})
export class ProductManagerComponent implements OnInit {

  @Input()
  product?: Product;
  @Input()
  author?: Author;
  @Input()
  language?: Language;
  @Input()
  coverType?: CoverType;
  @Input()
  genre?: Genre;
  @Input()
  publisher?: Publisher;

  constructor(private productService: ProductService,
              private authorService: AuthorService) {
  }

  ngOnInit(): void {
    console.log('author: ' + this.author.authorName);
  }

}
