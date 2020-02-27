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



}

