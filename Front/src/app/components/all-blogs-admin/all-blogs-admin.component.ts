import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";
import {TagService} from "../../service/tag.service";
import {BlogService} from "../../service/blog.service";
import {TagModel} from "../../model/tag.model";

@Component({
  selector: 'app-all-blogs-admin',
  template: `
    <div style="text-align: center">
      <p  style="color: black;font-size:40px; font-weight: bolder; font-family: 'Lucida Grande'; margin-bottom: 5%">List of blogs</p>
    </div>
    <h4>Search blogs by tags</h4>
    <div
      style=" margin-left: 5%; margin-right: 20%" *ngFor="let t of tagsList">
      <a  href="blogerPage" onclick="return false;" (click)="search(t)"><b>{{t.tagName}} </b> </a>
    </div>
    <div
      style="text-align: center; margin-left: 20%;margin-right: 20%">
    <ul>
      <li *ngFor="let blog of blogs | paginate: { itemsPerPage: 2, currentPage: p }">
        <div style="text-align: center;border-style: solid;border-width: 2px;border-color: darkgray;margin-top: 20px;">
          <div *ngFor="let tag of blog.tags">
            <p style="font-size: 20px ; color: dodgerblue"> <b>#{{tag.tagName}}</b></p>
          </div>
          <p style="font-size: 20px">Blog body: <b> {{blog.blogTitle}} </b></p>
          <p style="font-size: 20px">Blog body: <b> {{blog.blogBody}} </b></p>
          <br>
          <p style="font-size: 20px ">User: <b> {{blog.user.firstName}} {{blog.user.lastName}} </b></p>
          <p style="font-size: 20px">Date published: <b> {{blog.date}} </b></p>
          <div style="margin-bottom: 20px">

          </div>
       </div>
      </li>
    </ul>
    </div>
    <pagination-controls (pageChange)="p = $event"></pagination-controls>
    `,
  styleUrls: ['./all-blogs-admin.component.css']
})
export class AllBlogsAdminComponent implements OnInit {


  blogs = [];
  tags = [];
  tagsList = [];
  p: number = 1;

  myS: any ;
  public form: FormGroup;

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private authService: AuthService,
              private tagService: TagService,
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
    this.tagService.getAllTags().subscribe(res => {
      this.tagsList = res;
    })

    this.blogService.getAllBlogs().subscribe(data => {
      this.blogs = data;

    })
  }

  logout()
  {
    this.authService.logOutUser();
  }

  search(tag: TagModel)
  {
    this.blogService.searchBlogsByTag(tag).subscribe(data => {
      this.blogs = data ;
    })
  }

}
