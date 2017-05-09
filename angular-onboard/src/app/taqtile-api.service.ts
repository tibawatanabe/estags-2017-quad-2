import { Injectable } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { UserInfoService } from './user-info.service';

@Injectable()
export class TaqtileApiService {
  baseUrl: string;

  constructor(
    private http: Http,
    private userInfoService: UserInfoService
  ) {
    this.baseUrl = 'https://tq-template-node.herokuapp.com';
  }

  login(user: string, password: string): Observable<any> {
    let body = { user, password };
    return this.http.post(`${this.baseUrl}/authenticate`, body)
                    .map(response => response.json());
  }

  getUsers(size: number, page: number): Observable<any> {
    let headers = new Headers();
    headers.append('Authorization', this.userInfoService.getToken());

    let params = new URLSearchParams();

    params.append('pagination', JSON.stringify({ window: size, page: page }));

    return this.http.get(`${this.baseUrl}/users`, { params, headers })
                    .map(response => response.json());
  }

  getUser(id: string): Observable<any> {
    let headers = new Headers();
    headers.append('Authorization', this.userInfoService.getToken());

    return this.http.get(`${this.baseUrl}/user/${id}`, {headers})
                    .map(response => response.json());
  }

  createUser(name: string, email: string, password: string, type: string) {
    let headers = new Headers();
    headers.append('Authorization', this.userInfoService.getToken());

    let body = { name, email, password, type };

    return this.http.post(`${this.baseUrl}/user`, body, {headers})
                    .map(response => response.json());
  }

  edit(name: string, email: string, id: string) {
    let headers = new Headers();
    headers.append('Authorization', this.userInfoService.getToken());

    let body = { name, email };

    return this.http.put(`${this.baseUrl}/user/${id}`, body, {headers})
                    .map(response => response.json());
  }

}
