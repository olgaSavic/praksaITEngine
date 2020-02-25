import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {


  constructor(private authService: AuthService,
              protected router: Router,
              private userService: UserService) { }

  ngOnInit() {

  }

  logout()
  {
    this.authService.logOutUser();
  }

}
