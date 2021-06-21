import { Component, OnInit } from '@angular/core';
import {CategoriesPartComponent} from '../categories-part/categories-part.component';
import {ProductService} from '../../_service/product/product.service';
import {Product} from '../../_model/product';
import {Author} from '../../_model/author';
import {AuthorService} from '../../_service/categories/author.service';
import {CoverTypeService} from '../../_service/categories/cover-type.service';
import {GenreService} from '../../_service/categories/genre.service';
import {LanguageService} from '../../_service/categories/language.service';
import {PublisherService} from '../../_service/categories/publisher.service';
import {CoverType} from '../../_model/cover-type';
import {Genre} from '../../_model/genre';
import {Language} from '../../_model/Language';
import {Publisher} from '../../_model/Publisher';

@Component({
  selector: 'app-manager-workspace',
  templateUrl: './manager-workspace.component.html',
  styleUrls: ['./manager-workspace.component.css']
})
export class ManagerWorkspaceComponent implements OnInit {

  constructor(private categoriesPartComponent: CategoriesPartComponent,
              private productService: ProductService,
              private authorService: AuthorService,
              private coverTypeService: CoverTypeService,
              private genreService: GenreService,
              private languageService: LanguageService,
              private publisherService: PublisherService) { }

  page = 1;
  size = 5;
  products: Product[] = [];
  authors: Author[] = [];
  coverTypes: CoverType[] = [];
  genres: Genre[] = [];
  languages: Language[] = [];
  publishers: Publisher[] = [];

  ngOnInit(): void {
    this.getAllProducts();
    this.getAuthors();
    this.getCoverTypes();
    this.getGenres();
    this.getLanguages();
    this.getPublishers();
  }

  getAllProducts(): void {
    this.productService.getAllProducts(this.page, this.size)
      .subscribe(products => {
        console.log(products);
        this.products = products;
      }, error => console.log(error));

  }
  getAuthors(): any {
    this.authorService.getAllAuthors()
      .subscribe(authors => {
        this.authors = authors;
      });
  }

  getCoverTypes(): void {
    this.coverTypeService.getAllCoverTypes()
      .subscribe(coverTypes => {
        this.coverTypes = coverTypes;
      });
  }

  getGenres(): void {
    this.genreService.getAllGenres()
      .subscribe(genres => {
        this.genres = genres;
      });
  }

  getLanguages(): void {
    this.languageService.getAllLanguages()
      .subscribe(languages => {
        this.languages = languages;
      });
  }

  getPublishers(): void {
    this.publisherService.getAllPublishers()
      .subscribe(publishers => {
        this.publishers = publishers;
      });
  }

  update($event: string): void {
    this.getAllProducts();
  }
}
