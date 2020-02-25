import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from "../../service/auth.service";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-admin-blogers',
  templateUrl: './admin-blogers.component.html',
  styleUrls: ['./admin-blogers.component.css']
})
export class AdminBlogersComponent implements OnInit {
  blogers = [];
  public form: FormGroup;

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private authService: AuthService,
              private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getAllBlogers().subscribe(data => {
      this.blogers = data;
    })
  }

  logout()
  {
    this.authService.logOutUser();
  }

  addBloger() {
    const idRent = this.route.snapshot.params.idRent ;
    this.router.navigateByUrl('adminPage/bloger/add/');
  }

  editBloger(id: any) {
    this.router.navigateByUrl('adminPage/bloger/edit/' + id);

  }

  deleteBloger(id: any) {
    this.userService.deleteUser(id).subscribe(data => {
      location.reload();
    });

  }

  goBack() {
    this.router.navigateByUrl('adminPage' );
  }


}
