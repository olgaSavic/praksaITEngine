import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {BlogModel} from "../model/blog.model";
import {TagModel} from "../model/tag.model";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};

@Injectable()
export class TagService {
  private BASE_URL = 'api/tags';

  constructor(private http: HttpClient) {

  }

  getAllTags(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllTags`, {headers});
  }

  returnTagsOfBlog(id:any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/returnTagsOfBlog/${id}`, {headers});
  }

  addTagToBlog(id:any, object: TagModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/addTagToBlog/${id}`, body, {headers});
  }



}

