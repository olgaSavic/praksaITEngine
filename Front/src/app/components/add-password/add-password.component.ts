import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserModel} from "../../model/user.model";
import {AuthService} from "../../service/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-add-password',
  templateUrl: './add-password.component.html',
  styleUrls: ['./add-password.component.css']
})
export class AddPasswordComponent implements OnInit {

  public form: FormGroup;

  public pass2: AbstractControl;
  public pass: AbstractControl;

  user : UserModel = new UserModel();

  constructor(private authService : AuthService, public fb: FormBuilder,
              private route: ActivatedRoute,
              private http: HttpClient, private userService: UserService,
              private router: Router) {
    this.form = this.fb.group({

      'pass2': ['', Validators.compose([Validators.required])],
      'pass': ['', Validators.compose([Validators.required])]
    })

    this.pass2 = this.form.controls['pass2'];
    this.pass = this.form.controls['pass'];
  }

  ngOnInit() {
  }
  confirmClick(){

    const email = this.route.snapshot.params.email;

    if (this.pass.value != this.pass2.value)
    {
      alert('You have to enter same passwords!');
    }
    else {
      this.user.pass = this.pass.value;
      this.user.email = email;

      this.userService.editUserPassword(email, this.user).subscribe(data => {
        alert("You can login now!");
        this.router.navigateByUrl('login');
      })
    }

  }

}
