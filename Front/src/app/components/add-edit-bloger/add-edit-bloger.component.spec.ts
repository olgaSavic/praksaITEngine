import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditBlogerComponent } from './add-edit-bloger.component';

describe('AddEditBlogerComponent', () => {
  let component: AddEditBlogerComponent;
  let fixture: ComponentFixture<AddEditBlogerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditBlogerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditBlogerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
