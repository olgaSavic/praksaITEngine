import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-bloger-page',
  templateUrl: './bloger-page.component.html',
  styleUrls: ['./bloger-page.component.css']
})
export class BlogerPageComponent implements OnInit {

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
