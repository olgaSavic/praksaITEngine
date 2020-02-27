import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';
import {AuthService} from '../../service/auth.service';
import {TagService} from '../../service/tag.service';
import {BlogService} from '../../service/blog.service';
import {TagModel} from '../../model/tag.model';
import {CommentService} from '../../service/comment.service';

@Component({
  selector: 'app-show-comments',
  template: `
    <div class="items-wrapper__item items-wrapper__item-small items-wrapper__item--blue">
      <igx-navbar style="font-family: 'Lucida Grande'" title="B L O G">
        <button class="btn-primary" (click)="ulogujSe()">Login</button>
        <button class="btn-primary" (click)="registrujSe()"> Register</button>
      </igx-navbar>
    </div>
    <div style="text-align: center">
      <p  style="color: black;font-size:40px; font-weight: bolder; font-family: 'Lucida Grande'; margin-bottom: 5%">List of blog comments</p>
    </div>
    
    <div
      style="text-align: center; margin-left: 20%;margin-right: 20%">
    <ul>
      <li *ngFor="let comment of comments | paginate: { itemsPerPage: 2, currentPage: p }">
        <div style="text-align: center;border-style: solid;border-width: 2px;border-color: darkgray;margin-top: 20px;">

          <p style="font-size: 20px"><b> "{{comment.value}}" </b></p>
          <br>
          <p style="font-size: 20px ">User: <b> {{comment.name}}</b></p>
          <p style="font-size: 20px">Date published: <b> {{comment.date}} </b></p>
          <div style="margin-bottom: 20px">
            
          </div>
       </div>
      </li>
    </ul>
      <button class="btn btn-primary" style="width: 100% ; margin-top: 20px" (click)="exit()">Close</button>

    </div>
    <pagination-controls (pageChange)="p = $event"></pagination-controls>
    `,
  styleUrls: ['./show-comments.component.css']
})
export class ShowCommentsComponent implements OnInit {

  comments = [];
  p: number = 1;

  myS: any ;
  public form: FormGroup;

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private authService: AuthService,
              private tagService: TagService,
              private commentService: CommentService,
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
    const id = this.route.snapshot.params.id ;
    this.commentService.getBlogComments(id).subscribe(res => {
      this.comments = res;
    })
  }

  exit() {
    this.router.navigateByUrl('allBlogs' );
  }

  ulogujSe() {
    this.router.navigateByUrl('/login');
  }

  registrujSe() {
    this.router.navigateByUrl('/register');
  }

}
