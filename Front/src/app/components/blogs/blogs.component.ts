import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";
import {UserService} from "../../service/user.service";
import {BlogService} from "../../service/blog.service";

@Component({
  selector: 'app-blogs',
  template: `
    <div style="text-align: center">
      <p  style="color: black;font-size:40px; font-weight: bolder; font-family: 'Lucida Grande'; margin-bottom: 5%">List of blogs</p>
    </div>

    <div>
      <button class="btn btn-outline-primary" style="width: 40%;margin-top: 20px; font-size: 1.5em;margin-bottom: 30px;" (click)="addBlog()">Add new blog</button>
    </div>

    <div
      style="text-align: center; margin-left: 20%;margin-right: 20%">
    <ul>
      <li *ngFor="let blog of blogs | paginate: { itemsPerPage: 2, currentPage: p }">
        <div style="text-align: center;border-style: solid;border-width: 2px;border-color: darkgray;margin-top: 20px;">
          <p style="font-size: 20px">Blog body: <b> {{blog.blogTitle}} </b></p>
          <p style="font-size: 20px">Blog body: <b> {{blog.blogBody}} </b></p>

          <div style="margin-bottom: 20px">

            <button
              (click)="editBlog(blog.id)"
              style="border-radius: 12px;background-color: #1a8cff;color: white;height: 45px; width: 110px;font-size: 20px;margin-right: 20px">Edit
            </button>

            <button
              (click)="deleteBlog(blog.id)"
              style="border-radius: 12px;background-color: #1a8cff;height: 45px; width: 110px;color: white;font-size: 20px;margin-right: 30px">Delete
            </button>
          </div>
       </div>
      </li>
    </ul>
    </div>

    <pagination-controls (pageChange)="p = $event"></pagination-controls>
    `,
  styleUrls: ['./blogs.component.css']
})
export class BlogsComponent implements OnInit {

  blogs = [];
  p: number = 1;

  myS: any ;
  public form: FormGroup;

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private authService: AuthService,
              private blogService: BlogService) {

    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };

    this.myS = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        // Trick the Router into believing it's last link wasn't previously loaded
        this.router.navigated = false;
      }
    });
  }

  ngOnInit() {
    this.blogService.getAllBlogs().subscribe(data => {
      this.blogs = data;
    })
  }

  logout()
  {
    this.authService.logOutUser();
  }

  addBlog() {
    const idRent = this.route.snapshot.params.idRent ;
    this.router.navigateByUrl('blogerPage/blog/add/');
  }

  editBlog(id: any) {
    this.blogService.canEditDeleteBlog(id).subscribe(res => {
      if (res == true) // dozvoljena je izmena, jer je njegov blog
      {
        this.router.navigateByUrl('blogerPage/blog/edit/' + id);
      }
      else {
        alert("It is possible to modify only your blog!");
      }

    })


  }

  deleteBlog(id: any) {
    this.blogService.canEditDeleteBlog(id).subscribe(res => {
      if (res == true) // dozvoljeno je brisanje, jer je njegov blog
      {
        this.blogService.deleteBlog(id).subscribe(data => {
          if (this.myS) {
            this.myS.unsubscribe();
            this.router.navigateByUrl('blogerPage' );
          }
        });
      }
      else {
        alert("It is possible to delete only your blog!");
      }

    })


  }

  goBack() {
    this.router.navigateByUrl('blogerPage' );
  }

}
