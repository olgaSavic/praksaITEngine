import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserModel} from "../../model/user.model";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../service/auth.service";
import {UserService} from "../../service/user.service";
import * as $ from 'jQuery' ;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public form: FormGroup;

  public email: AbstractControl;
  public pass: AbstractControl;

  user : UserModel = new UserModel();

  constructor(private authService : AuthService, public fb: FormBuilder,
              private route: ActivatedRoute,
              private http: HttpClient, private userService: UserService,
              private router: Router) {
    this.form = this.fb.group({

      'email': ['', Validators.compose([Validators.required])],
      'pass': ['', Validators.compose([Validators.required])]
    })

    this.email = this.form.controls['email'];
    this.pass = this.form.controls['pass'];
  }

  ngOnInit() {
  }


  confirmClick(){

      this.user.email = this.email.value;
      this.user.pass = this.pass.value;
      this.authService.login(this.user).subscribe(
        success => {

          if (!success) {
            alert('Email od password is not correct!');
          } else {
            this.authService.getCurrentUser().subscribe(
              data => {

                  localStorage.setItem("ROLE", data.role);
                  localStorage.setItem("USERNAME", data.email);

                  if (localStorage.getItem("ROLE") == "ADMIN") {
                    this.router.navigate(["/adminPage"]);
                  } else if (localStorage.getItem("ROLE") == "BLOGER") {
                    this.router.navigate(["/blogerPage"])
                  }
              }
            )
          }
        }
      )

  }


}
