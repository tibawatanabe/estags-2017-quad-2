import { Injectable } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { UserInfoService } from './user-info.service';

@Injectable()
export class TaqtileApiService {
  baseUrl: string;

  constructor(
    private http: Http,
    private _userInfoService: UserInfoService
  ) {
    this.baseUrl = 'https://tq-template-node.herokuapp.com';
  }

  login(user: string, password: string): Observable<any> {
    let body = { user, password };
    return this.http.post(`${this.baseUrl}/authenticate`, body)
                    .map(response => response.json());
  }

  getUsers(): Observable<any> {
    let headers = new Headers();
    headers.append('Authorization', this._userInfoService.getToken());

    let params = new URLSearchParams();

    params.append('pagination', JSON.stringify({ window: 10, page: 1 }));

    return this.http.get(`${this.baseUrl}/users`, { params, headers })
                    .map(response => response.json());
  }

  getUser(id: string): Observable<any> {
    let headers = new Headers();
    headers.append('Authorization', this._userInfoService.getToken());

    return this.http.get(`${this.baseUrl}/user/${id}`, {headers})
                    .map(response => response.json());
  }

  createUser(name: string, email: string, password: string, type: string) {
    let headers = new Headers();
    headers.append('Authorization', this._userInfoService.getToken());

    let body = { name, email, password, type };

    return this.http.post(`${this.baseUrl}/user`, body, {headers})
                    .map(response => response.json());
  }

}
