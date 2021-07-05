import { Component } from '@angular/core';
import {BaseLinkComponent} from "../base-link/base-link.component";
import {LinkService} from "../../services/link.service";

@Component({
  selector: 'app-compare-link',
  templateUrl: './compare-link.component.html',
  styleUrls: ['./compare-link.component.css', '../base-link/base-link.component.css']
})
export class CompareLinkComponent extends BaseLinkComponent {

  url = 'compare';
  constructor(linkService: LinkService) {
    super(linkService);
  }

}
