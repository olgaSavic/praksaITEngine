import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import * as $ from 'jQuery';
import {HttpClient} from "@angular/common/http";
import {UserModel} from "../../model/user.model";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public form: FormGroup;

  public firstName: AbstractControl;
  public lastName: AbstractControl;
  public email: AbstractControl;
  public pass: AbstractControl;

  korisnik: UserModel = new UserModel();

  constructor(private http: HttpClient, public fb: FormBuilder,
              private route: ActivatedRoute,
              private userService: UserService,
              private router: Router) {
    this.form = this.fb.group({
      'firstName': ['', Validators.compose([Validators.required])],
      'lastName': ['', Validators.compose([Validators.required])],
      'email': ['', Validators.compose([Validators.required])],
      'pass': ['', Validators.compose([Validators.required])]
    })

    this.firstName = this.form.controls['firstName'];
    this.lastName = this.form.controls['lastName'];
    this.email = this.form.controls['email'];
    this.pass = this.form.controls['pass'];
  }

  ngOnInit() {
  }

  confirmClick() {
    this.korisnik.firstName = this.firstName.value;
    this.korisnik.lastName = this.lastName.value;
    this.korisnik.email = this.email.value;
    this.korisnik.pass = this.pass.value;

    this.userService.register(this.korisnik).subscribe(
        data => {
          this.router.navigateByUrl('/homepage');
        },
        error => {
          alert("Email already exists!");

        });

  }

}
