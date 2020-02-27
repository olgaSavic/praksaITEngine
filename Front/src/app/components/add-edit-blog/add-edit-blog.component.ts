import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";
import {UserService} from "../../service/user.service";
import {UserModel} from "../../model/user.model";
import {BlogService} from "../../service/blog.service";
import {BlogModel} from "../../model/blog.model";

@Component({
  selector: 'app-add-edit-blog',
  templateUrl: './add-edit-blog.component.html',
  styleUrls: ['./add-edit-blog.component.css']
})
export class AddEditBlogComponent implements OnInit {
  public form: FormGroup;

  public blogTitle: AbstractControl;
  public blogBody: AbstractControl;

  naslovStranice: string;
  public method_name = 'ADD';

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private authService: AuthService,
              private blogService: BlogService) {

    this.form = this.fb.group({

      'blogTitle': ['', Validators.compose([Validators.required,  Validators.pattern('[A-Za-z]+$')])],
      'blogBody': ['', Validators.compose([Validators.required])]
    })

    this.blogTitle = this.form.controls['blogTitle'];
    this.blogBody = this.form.controls['blogBody'];

  }

  ngOnInit() {

    const id = this.route.snapshot.params.id;
    const mode = this.route.snapshot.params.mode;

    if (mode == 'edit') {
      this.method_name = 'EDIT';
      this.naslovStranice = 'Edit blog page';

      this.blogService.returnBlogById(id).subscribe(data => {
        this.form.controls['blogTitle'].setValue(data.blogTitle);
        this.form.controls['blogBody'].setValue(data.blogBody);
      })
    } else if (mode == 'add') {
      this.method_name = 'ADD';
      this.naslovStranice = 'Add blog page';
    }
  }

  confirmClick() {
    if (this.method_name === 'ADD') {
      this.createBlog();
    } else {
      this.editBlog();
    }
  }

  logout()
  {
    this.authService.logOutUser();
  }

  createBlog()
  {
    const blog = new BlogModel();
    blog.blogTitle = this.blogTitle.value ;
    blog.blogBody = this.blogBody.value ;


    this.blogService.addNewBlog(blog).subscribe(data => {
      this.router.navigateByUrl('blogerPage');
    },
      error => {
        alert("Incorrect input for fields!");
      })
  }

  editBlog()
  {

    const id = this.route.snapshot.params.id;

    const blog = new BlogModel();
    blog.blogTitle = this.blogTitle.value ;
    blog.blogBody = this.blogBody.value ;

    this.blogService.editBlog(id, blog).subscribe(data =>
    {
      this.router.navigateByUrl('blogerPage');
    })
  }

  exit()
  {
    this.router.navigateByUrl('blogerPage');
  }


}
