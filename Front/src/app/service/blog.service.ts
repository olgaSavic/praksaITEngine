import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {BlogModel} from "../model/blog.model";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};

@Injectable()
export class BlogService {
  private BASE_URL = 'api/blogs';

  constructor(private http: HttpClient) {

  }

  getAllBlogs(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllBlogs`, {headers});
  }


  editBlog(id:any, object: BlogModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/editBlog/${id}`, body, {headers});
  }


  addNewBlog(object: BlogModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/addNewBlog`, body, {headers});
  }

  deleteBlog(id: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.delete(`${this.BASE_URL}/deleteBlog/${id}`, {headers});
  }

  canEditDeleteBlog(id: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/canEditDeleteBlog/${id}`, {headers});
  }

  returnBlogById(id: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/returnBlogById/${id}`, {headers});
  }




}

