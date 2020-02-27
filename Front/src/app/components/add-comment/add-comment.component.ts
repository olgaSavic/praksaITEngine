import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../service/auth.service';
import {BlogService} from '../../service/blog.service';
import {TagModel} from '../../model/tag.model';
import {CommentModel} from '../../model/comment.model';
import {CommentService} from '../../service/comment.service';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent implements OnInit {

  public form: FormGroup;

  public comment: AbstractControl;
  public name: AbstractControl;


  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private authService: AuthService,
              private commentService: CommentService,
              private blogService: BlogService) {

    this.form = this.fb.group({

      'comment': ['', Validators.compose([Validators.required])],
      'name': ['', Validators.compose([Validators.required])]

    })

    this.comment = this.form.controls['comment'];
    this.name = this.form.controls['name'];


  }

  ngOnInit() {
  }


  confirmClick()
  {
    const id = this.route.snapshot.params.id;
    const comment = new CommentModel();
    comment.value = this.comment.value ;
    comment.name = this.name.value ;

    this.commentService.addCommentToBlog(id, comment).subscribe(data => {
        this.router.navigateByUrl('allBlogs');
      },
      error => {
        alert("Incorrect input for fields!");
      })
  }

  exit()
  {
    this.router.navigateByUrl('allBlogs');
  }

  ulogujSe() {
    this.router.navigateByUrl('/login');
  }

  registrujSe() {
    this.router.navigateByUrl('/register');
  }

}
