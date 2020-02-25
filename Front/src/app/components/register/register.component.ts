import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Router} from "@angular/router";
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

  registerForm: FormGroup;

  korisnik: UserModel = new UserModel();

  poruka = '';

  constructor(private http: HttpClient, private userService: UserService, private router: Router) {
  }

  ngOnInit() {
  }

  submit() {
    let provera : boolean = false;

    if (this.korisnik.firstName == "" || this.korisnik.firstName == undefined) {
      $("#nameValue").addClass('border-danger');
      provera = true;
    } else {
      $('#nameValue').removeClass('border-danger');
    }
    if (this.korisnik.lastName == "" || this.korisnik.lastName == undefined) {
      $('#surname').addClass('border-danger');
      provera = true;
    } else {
      $('#surname').removeClass('border-danger');
    }

    if (this.korisnik.pass == "" || this.korisnik.pass == undefined) {
      $('#passValue').addClass('border-danger');
      provera = true;
    } else {
      $('#passValue').removeClass('border-danger');
    }

    if (this.korisnik.email == "" || this.korisnik.email == undefined) {
      $('#email').addClass('border-danger');
      provera = true;
    } else {
      $('#email').removeClass('border-danger');
    }

    if (!provera) {
      this.userService.register(this.korisnik).subscribe(
        data => {
          this.router.navigateByUrl('/homepage');
        },
        error => {
          this.poruka = 'Email already exists!';

        }
      );
    }
  }

}
