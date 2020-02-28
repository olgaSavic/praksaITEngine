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

  public newPass: any ;

  constructor(protected router: Router,
              private userService: UserService,
              public fb: FormBuilder
  ) {
    this.form = this.fb.group({
      'firstName': ['', Validators.compose([Validators.required])],
      'lastName': ['', Validators.compose([Validators.required])],
      'pass': ['']
    })
    this.firstName = this.form.controls['firstName'];
    this.lastName = this.form.controls['lastName'];
    this.pass = this.form.controls['pass'];

  }

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(data => {
      this.form.controls['firstName'].setValue(data.firstName);
      this.form.controls['lastName'].setValue(data.lastName);
      //this.form.controls['pass'].setValue('');
      this.newPass = data.pass;
    },
      error => {
        this.router.navigateByUrl('adminPage');

      });
  }

  saveChanges() {
      this.editUser();
  }

  editUser() {
    const admin = new UserModel();
    admin.firstName = this.firstName.value ;
    admin.lastName = this.lastName.value ;
    admin.pass = this.pass.value;

    /*
    if (this.pass.value == null || this.pass.value == '' || this.pass.value == undefined)
    {
      admin.pass = this.newPass;
      alert('Stara lozinka: ' + admin.pass);
    }
    else {
      admin.pass = this.pass.value;
      alert('Nova lozinka: ' + admin.pass);

    }
     */

    this.userService.editCurrentUser(admin).subscribe(data => {
      this.redirectTo('/adminPage');
    },
      error => {
        alert('Incoorrect input for fields!');
      });
  }

  redirectTo(uri: string) {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
      this.router.navigate([uri]));
  }

}
