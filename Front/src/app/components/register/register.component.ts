import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from "@angular/common/http";
import {UserModel} from "../../model/user.model";
import {UserService} from "../../service/user.service";
import {FileUploader} from "ng2-file-upload";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  @ViewChild('fileInput', null) fileInput: ElementRef;

  uploader: FileUploader;
  isDropOver: boolean;

  ngOnInit(): void {
    const headers = [{name: 'Accept', value: 'application/json'}];
    this.uploader = new FileUploader({url: 'api/files/add', autoUpload: true, headers: headers});
    this.uploader.onCompleteAll = () =>  {
      alert('File uploaded');
      this.imgUrl = this.fileInput.nativeElement.files[0].name ;
      alert(this.imgUrl);
      console.log(this.fileInput.nativeElement);
    };
  }

  fileOverAnother(e: any): void {
    this.isDropOver = e;
  }

  fileClicked() {
    this.fileInput.nativeElement.click();
  }

  public form: FormGroup;

  public firstName: AbstractControl;
  public lastName: AbstractControl;
  public email: AbstractControl;
  public pass: AbstractControl;

  public imgUrl: any ;

  korisnik: UserModel = new UserModel();



  constructor(private http: HttpClient, public fb: FormBuilder,
              private route: ActivatedRoute,
              private userService: UserService,
              private router: Router) {
    this.form = this.fb.group({
      'firstName': ['', Validators.compose([Validators.required])],
      'lastName': ['', Validators.compose([Validators.required])],
      'email': ['', Validators.compose([Validators.required])],
      'pass': ['', Validators.compose([Validators.required])]
    })

    this.firstName = this.form.controls['firstName'];
    this.lastName = this.form.controls['lastName'];
    this.email = this.form.controls['email'];
    this.pass = this.form.controls['pass'];
  }

  confirmClick() {
    this.korisnik.firstName = this.firstName.value;
    this.korisnik.lastName = this.lastName.value;
    this.korisnik.email = this.email.value;
    this.korisnik.pass = this.pass.value;
    this.korisnik.imagePath = this.imgUrl;

    this.userService.register(this.korisnik).subscribe(
        data => {
          this.router.navigateByUrl('/homepage');
        },
        error => {
          alert("Email already exists!");

        });

  }

}
