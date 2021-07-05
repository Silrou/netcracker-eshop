import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompareLinkComponent } from './compare-link.component';

describe('CompareLinkComponent', () => {
  let component: CompareLinkComponent;
  let fixture: ComponentFixture<CompareLinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompareLinkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompareLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
