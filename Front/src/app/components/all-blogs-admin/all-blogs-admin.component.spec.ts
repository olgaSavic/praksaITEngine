import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllBlogsAdminComponent } from './all-blogs-admin.component';

describe('AllBlogsAdminComponent', () => {
  let component: AllBlogsAdminComponent;
  let fixture: ComponentFixture<AllBlogsAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllBlogsAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllBlogsAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
