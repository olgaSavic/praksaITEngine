import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";
import {BlogService} from "../../service/blog.service";
import {BlogModel} from "../../model/blog.model";
import {TagModel} from "../../model/tag.model";
import {TagService} from '../../service/tag.service';

@Component({
  selector: 'app-add-tag-blog',
  templateUrl: './add-tag-blog.component.html',
  styleUrls: ['./add-tag-blog.component.css']
})
export class AddTagBlogComponent implements OnInit {
  public form: FormGroup;

  public tagName: AbstractControl;

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private authService: AuthService,
              private tagService: TagService,
              private blogService: BlogService) {

    this.form = this.fb.group({

      'tagName': ['', Validators.compose([Validators.required, Validators.pattern('[A-Za-z]+$')])]
    })

    this.tagName = this.form.controls['tagName'];

  }

  ngOnInit() {
  }

  logout()
  {
    this.authService.logOutUser();
  }

  createTag()
  {
    const id = this.route.snapshot.params.id;
    const tag = new TagModel();
    tag.tagName = this.tagName.value ;

    this.tagService.addTagToBlog(id, tag).subscribe(data => {
      this.router.navigateByUrl('blogerPage');
    },
      error => {
      alert("Incorrect input for fields!");
      })
  }

  exit()
  {
    this.router.navigateByUrl('blogerPage');
  }

}
