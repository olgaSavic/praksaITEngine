import { Component, OnInit } from '@angular/core';
import {FormGroup} from "@angular/forms";
import {UserModel} from "../../model/user.model";
import {Router} from "@angular/router";
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
  loginForm: FormGroup;

  user : UserModel = new UserModel();
  errorMessage : String;

  poruka = '';

  constructor(private authService : AuthService, private http: HttpClient, private userService: UserService, private router: Router) { }

  ngOnInit() {
  }


  clickLogIn(){

    let provera : boolean = false;


    if(this.user.email == "" || this.user.email == undefined){
      $("#emailValue").addClass('border-danger');
      provera = true;
    } else {
      $("#emailValue").removeClass('border-danger');
    }

    if(this.user.pass == "" || this.user.pass == undefined){
      $("#passValue").addClass('border-danger');
      provera = true;
    } else {
      $("#passValue").removeClass('border-danger');
    }

    if(!provera) {
      this.authService.login(this.user).subscribe(
        success => {

          if (!success) {
            this.poruka = "Incorrect email or password!";
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


}
