import {Component, OnInit} from '@angular/core';
import {typesOfCategories} from "../../_model/typesOfCategories";
import {GenreService} from "../../_service/categories/genre.service";
import {Genre} from "../../_model/genre";
import {AuthorService} from "../../_service/categories/author.service";
import {CoverTypeService} from "../../_service/categories/cover-type.service";
import {LanguageService} from "../../_service/categories/language.service";
import {PublisherService} from "../../_service/categories/publisher.service";
import {Author} from "../../_model/author";
import {CoverType} from "../../_model/cover-type";
import {Language} from "../../_model/Language";
import {Publisher} from "../../_model/Publisher";

@Component({
  selector: 'app-categories-part',
  templateUrl: './categories-part.component.html',
  styleUrls: ['./categories-part.component.css']
})
export class CategoriesPartComponent implements OnInit {

  typesOfCategories = typesOfCategories;
  authors: Author[] = [];
  coverTypes: CoverType[] = [];
  genres: Genre[] = [];
  languages: Language[] = [];
  publishers: Publisher[] = [];
  authorsCheckedSet;
  coverTypesCheckedSet;
  genresCheckedSet;
  languagesCheckedSet;
  publishersCheckedSet;

  constructor(
    private authorService: AuthorService,
    private coverTypeService: CoverTypeService,
    private genreService: GenreService,
    private languageService: LanguageService,
    private publisherService: PublisherService,
  ) {
  }

  ngOnInit(): void {
    this.getAuthors();
    this.getCoverTypes();
    this.getGenres();
    this.getLanguages();
    this.getPublishers();
    this.authorsCheckedSet = new Set();
    this.coverTypesCheckedSet = new Set();
    this.genresCheckedSet = new Set();
    this.languagesCheckedSet = new Set();
    this.publishersCheckedSet = new Set();
  }

  filter(): void {
    console.log("authorchecked");
    for (let currentNumber of this.authorsCheckedSet) {
      console.log(currentNumber);
    }
    console.log("coverchecked");
    for (let currentNumber of this.coverTypesCheckedSet) {
      console.log(currentNumber);
    }
    console.log("genrechecked");
    for (let currentNumber of this.genresCheckedSet) {
      console.log(currentNumber);
    }
    console.log("languagechecked");
    for (let currentNumber of this.languagesCheckedSet) {
      console.log(currentNumber);
    }
    console.log("publisherchecked");
    for (let currentNumber of this.publishersCheckedSet) {
      console.log(currentNumber);
    }
  }

  authorChecked(id: number): void {
    if (this.authorsCheckedSet.has(id)) {
      this.authorsCheckedSet.delete(id);
    } else {
      this.authorsCheckedSet.add(id);
    }
  }

  coverTypeChecked(id: number): void {
    if (this.coverTypesCheckedSet.has(id)) {
      this.coverTypesCheckedSet.delete(id);
    } else {
      this.coverTypesCheckedSet.add(id);
    }
  }
  genreChecked(id: number): void {
    if (this.genresCheckedSet.has(id)) {
      this.genresCheckedSet.delete(id);
    } else {
      this.genresCheckedSet.add(id);
    }
  }
  languageChecked(id: number): void {
    if (this.languagesCheckedSet.has(id)) {
      this.languagesCheckedSet.delete(id);
    } else {
      this.languagesCheckedSet.add(id);
    }
  }
  publisherChecked(id: number): void {
    if (this.publishersCheckedSet.has(id)) {
      this.publishersCheckedSet.delete(id);
    } else {
      this.publishersCheckedSet.add(id);
    }
  }

  getAuthors(): void {
    this.authorService.getAllAuthors()
      .subscribe(authors => {
        this.authors = authors;
      })
  }

  getCoverTypes(): void {
    this.coverTypeService.getAllCoverTypes()
      .subscribe(coverTypes => {
        this.coverTypes = coverTypes;
      })
  }

  getGenres(): void {
    this.genreService.getAllGenres()
      .subscribe(genres => {
        this.genres = genres;
      })
  }

  getLanguages(): void {
    this.languageService.getAllLanguages()
      .subscribe(languages => {
        this.languages = languages;
      })
  }

  getPublishers(): void {
    this.publisherService.getAllPublishers()
      .subscribe(publishers => {
        this.publishers = publishers;
      })
  }
}
