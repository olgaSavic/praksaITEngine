import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBlogersComponent } from './admin-blogers.component';

describe('AdminBlogersComponent', () => {
  let component: AdminBlogersComponent;
  let fixture: ComponentFixture<AdminBlogersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminBlogersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminBlogersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
