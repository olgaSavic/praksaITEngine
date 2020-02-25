import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {UserModel} from "../../model/user.model";

@Component({
  selector: 'app-admin-profile-edit',
  templateUrl: './admin-profile-edit.component.html',
  styleUrls: ['./admin-profile-edit.component.css']
})

export class AdminProfileEditComponent implements OnInit {

  public form: FormGroup;
  public firstName: AbstractControl;
  public lastName: AbstractControl;
  public pass: AbstractControl;

  constructor(protected router: Router,
              private userService: UserService,
              public fb: FormBuilder
  ) {
    this.form = this.fb.group({
      'firstName': ['', Validators.compose([Validators.required])],
      'lastName': ['', Validators.compose([Validators.required])],
      'pass': ['', Validators.compose([Validators.required])]
    })
    this.firstName = this.form.controls['firstName'];
    this.lastName = this.form.controls['lastName'];
    this.pass = this.form.controls['pass'];

  }

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(data => {
      this.form.controls['firstName'].setValue(data.firstName);
      this.form.controls['lastName'].setValue(data.lastName);
      this.form.controls['pass'].setValue(data.pass);

    });
  }

  saveChanges() {
      this.editUser();
  }

  editUser() {
    const admin = new UserModel();
    admin.firstName = this.firstName.value ;
    admin.lastName = this.lastName.value ;
    admin.pass = this.pass.value ;
    this.userService.editCurrentUser(admin).subscribe(data => {
      this.redirectTo('/adminPage');
    });
  }

  redirectTo(uri: string) {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
      this.router.navigate([uri]));
  }

}
