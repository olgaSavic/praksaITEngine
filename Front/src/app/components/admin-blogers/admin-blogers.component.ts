import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';
import {AuthService} from "../../service/auth.service";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-admin-blogers',

  template: `
    <div style="text-align: center">
      <p  style="color: black;font-size:40px; font-weight: bolder; font-family: 'Lucida Grande'; margin-bottom: 5%">List of blogs</p>
    </div>

    <div>
      <button class="btn btn-outline-primary" style="width: 40%;margin-top: 20px; font-size: 1.5em;margin-bottom: 30px;" (click)="addBloger()">Add new blog</button>
    </div>

    <div
      style="text-align: center; margin-left: 20%;margin-right: 20%">
      <ul>
        <li *ngFor="let bloger of blogers | paginate: { itemsPerPage: 2, currentPage: p }">
          <div style="text-align: center;border-style: solid;border-width: 2px;border-color: darkgray;margin-top: 20px;">
            <p style="font-size: 20px">First name: <b> {{bloger.firstName}} </b></p>
            <p style="font-size: 20px">Last name: <b> {{bloger.lastName}} </b></p>
            <p style="font-size: 20px">Email: <b> {{bloger.email}} </b></p>

            <div style="margin-bottom: 20px">

              <button
                (click)="editBloger(bloger.id)"
                style="border-radius: 12px;background-color: #1a8cff;color: white;height: 45px; width: 110px;font-size: 20px;margin-right: 20px">Edit
              </button>

              <button
                (click)="deleteBloger(bloger.id)"
                style="border-radius: 12px;background-color: #1a8cff;height: 45px; width: 110px;color: white;font-size: 20px;margin-right: 30px">Delete
              </button>
            </div>
          </div>
        </li>
      </ul>
    </div>

    <pagination-controls (pageChange)="p = $event"></pagination-controls>
    `,

  styleUrls: ['./admin-blogers.component.css'],

})
export class AdminBlogersComponent implements OnInit {
  blogers = [];
  public form: FormGroup;
  p: number = 1;
  collection: any[] = this.blogers;
  myS: any ;
  data: any;

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private authService: AuthService,
              private userService: UserService) {


    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };

    this.myS = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        // Trick the Router into believing it's last link wasn't previously loaded
        this.router.navigated = false;
      }
    });
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
      if (this.myS) {
        this.myS.unsubscribe();
        this.router.navigateByUrl('adminPage' );
      }
    });

  }

  goBack() {
    this.router.navigateByUrl('adminPage' );
  }

  ngOnDestroy() {
    if (this.myS) {
      this.myS.unsubscribe();
    }
  }


}
