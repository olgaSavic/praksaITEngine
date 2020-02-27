import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {BlogModel} from "../model/blog.model";
import {TagModel} from "../model/tag.model";
import {CommentModel} from "../model/comment.model";


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

  getMyBlogs(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getMyBlogs`, {headers});
  }

  addCommentToBlog(id:any, object: CommentModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/addCommentToBlog/${id}`, body, {headers});
  }

  searchBlogsByTag(object: TagModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/searchBlogsByTag`, body, {headers});
  }

  searchMyBlogsByTag(object: TagModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/searchMyBlogsByTag`, body, {headers});
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

