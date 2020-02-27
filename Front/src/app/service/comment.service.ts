import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {CommentModel} from '../model/comment.model';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};

@Injectable()
export class CommentService {
  private BASE_URL = 'api/comments';

  constructor(private http: HttpClient) {

  }

  getBlogComments(id: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getBlogComments/${id}`, {headers});
  }


  addCommentToBlog(id:any, object: CommentModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/addCommentToBlog/${id}`, body, {headers});
  }



}

