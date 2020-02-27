import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTagBlogComponent } from './add-tag-blog.component';

describe('AddTagBlogComponent', () => {
  let component: AddTagBlogComponent;
  let fixture: ComponentFixture<AddTagBlogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddTagBlogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTagBlogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
