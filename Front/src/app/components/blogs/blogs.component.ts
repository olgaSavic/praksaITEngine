import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";
import {UserService} from "../../service/user.service";
import {BlogService} from "../../service/blog.service";

@Component({
  selector: 'app-blogs',
  templateUrl: './blogs.component.html',
  styleUrls: ['./blogs.component.css']
})
export class BlogsComponent implements OnInit {

  blogs = [];
  public form: FormGroup;

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private authService: AuthService,
              private blogService: BlogService) {
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
          location.reload();
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
