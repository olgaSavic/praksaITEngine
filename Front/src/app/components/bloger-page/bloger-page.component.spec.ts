import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BlogerPageComponent } from './bloger-page.component';

describe('BlogerPageComponent', () => {
  let component: BlogerPageComponent;
  let fixture: ComponentFixture<BlogerPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BlogerPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BlogerPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
