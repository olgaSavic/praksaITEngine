import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";
import {UserService} from "../../service/user.service";
import {UserModel} from "../../model/user.model";

@Component({
  selector: 'app-add-edit-bloger',
  templateUrl: './add-edit-bloger.component.html',
  styleUrls: ['./add-edit-bloger.component.css']
})
export class AddEditBlogerComponent implements OnInit {

  public form: FormGroup;

  public firstName: AbstractControl;
  public lastName: AbstractControl;
  public email: AbstractControl;
  public password: AbstractControl;

  naslovStranice: string;
  public method_name = 'ADD';

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private authService: AuthService,
              private userService: UserService) {

    this.form = this.fb.group({

      'firstName': ['', Validators.compose([Validators.required])],
      'lastName': ['', Validators.compose([Validators.required])],
      'email': ['', Validators.compose([Validators.required])],
      'password': ['', Validators.compose([Validators.required])]
    })

    this.firstName = this.form.controls['firstName'];
    this.lastName = this.form.controls['lastName'];
    this.email = this.form.controls['email'];
    this.password = this.form.controls['password'];
  }

  ngOnInit() {

    const id = this.route.snapshot.params.id;
    const mode = this.route.snapshot.params.mode;

    if (mode == 'edit') {
      this.method_name = 'EDIT';
      this.naslovStranice = 'Edit bloger page';

      this.userService.returnUserById(id).subscribe(data => {
        this.form.controls['firstName'].setValue(data.firstName);
        this.form.controls['lastName'].setValue(data.lastName);
        this.form.controls['email'].setValue(data.email);
        this.form.controls['password'].setValue(data.pass);

      })
    } else if (mode == 'add') {
      this.method_name = 'ADD';
      this.naslovStranice = 'Add bloger page';
    }
  }

  confirmClick() {
    if (this.method_name === 'ADD') {
      this.createBloger();
    } else {
      this.editBloger();
    }
  }

  logout()
  {
    this.authService.logOutUser();
  }

  createBloger()
  {
    const bloger = new UserModel();
    bloger.firstName = this.firstName.value ;
    bloger.lastName = this.lastName.value ;
    bloger.email = this.email.value ;
    bloger.pass = this.password.value ;

    this.userService.createNewUser(bloger).subscribe(data => {
      this.router.navigateByUrl('adminPage');
    })
  }

  editBloger()
  {

    const id = this.route.snapshot.params.id;

    const bloger = new UserModel();
    bloger.firstName = this.firstName.value ;
    bloger.lastName = this.lastName.value ;
    bloger.email = this.email.value ;
    bloger.pass = this.password.value ;

    this.userService.editUser(id, bloger).subscribe(data =>
    {
      this.router.navigateByUrl('adminPage');
    })
  }

  exit()
  {
    this.router.navigateByUrl('adminPage');
  }

}
