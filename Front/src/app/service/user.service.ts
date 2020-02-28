import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {UserModel} from "../model/user.model";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};

@Injectable()
export class UserService {
  private BASE_URL = 'api/users';

  constructor(private http: HttpClient) {

  }

  getAllAdmins(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllAdmins`, {headers});
  }

  getAllBlogers(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllBlogers`, {headers});
  }

  getAllUsers(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllUsers`, {headers});
  }

  getUserTypes(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getUserTypes`, {headers});
  }

  getCurrentUser(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getCurrentUser`, {headers});
  }

  editUser(id:any, object: UserModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/editUser/${id}`, body, {headers});
  }

  editUserPassword(email:any, object: UserModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/editUserPassword/${email}`, body, {headers});
  }

  editCurrentUser(object: UserModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/editCurrentUser`, body, {headers});
  }


  register(k: UserModel) {
    return this.http.post<UserModel>(`${this.BASE_URL}/register`, k);
  }

  createNewUser(object: UserModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/createNewUser`, body, {headers});
  }

  deleteUser(idUser: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.delete(`${this.BASE_URL}/deleteUser/${idUser}`, {headers});
  }

  returnUserById(idUser: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/returnUserById/${idUser}`, {headers});
  }

}
