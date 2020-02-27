import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {FormsModule} from "@angular/forms";
import {HomepageComponent} from "./components/homepage/homepage.component";
import {RegisterComponent} from './components/register/register.component';
import {LoginComponent} from './components/login/login.component';
import {AdminPageComponent} from "./components/admin-page/admin-page.component";
import {BlogerPageComponent} from "./components/bloger-page/bloger-page.component";
import {AddEditBlogerComponent} from "./components/add-edit-bloger/add-edit-bloger.component";
import {AddEditBlogComponent} from "./components/add-edit-blog/add-edit-blog.component";
import {AuthGuard} from "./components/guards/AuthGuard";
import {AddPasswordComponent} from "./components/add-password/add-password.component";
import {AddTagBlogComponent} from "./components/add-tag-blog/add-tag-blog.component";


const routes: Routes = [
  {path: '', component: HomepageComponent , pathMatch: 'full'},
  {path: 'homepage', component: HomepageComponent },
  {path: 'login', component: LoginComponent },
  {path: 'register', component: RegisterComponent },
  {path: 'adminPage', component: AdminPageComponent, canActivate: [AuthGuard] },
  {path: 'blogerPage', component: BlogerPageComponent, canActivate: [AuthGuard] },
  {path: 'adminPage/bloger/:mode/:id', component: AddEditBlogerComponent, canActivate: [AuthGuard] },
  {path: 'blogerPage/blog/:mode/:id', component: AddEditBlogComponent, canActivate: [AuthGuard]},
  {path: 'addPassword/:email', component: AddPasswordComponent },
  {path: 'blogerPage/addTag/:id', component: AddTagBlogComponent, canActivate: [AuthGuard]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: true}), FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
