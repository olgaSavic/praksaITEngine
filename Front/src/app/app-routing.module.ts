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


const routes: Routes = [
  {path: '', component: HomepageComponent , pathMatch: 'full'},
  {path: 'homepage', component: HomepageComponent },
  {path: 'login', component: LoginComponent },
  {path: 'register', component: RegisterComponent },
  {path: 'adminPage', component: AdminPageComponent },
  {path: 'blogerPage', component: BlogerPageComponent },
  {path: 'adminPage/bloger/:mode/:id', component: AddEditBlogerComponent},
  {path: 'blogerPage/blog/:mode/:id', component: AddEditBlogComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: true}), FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
