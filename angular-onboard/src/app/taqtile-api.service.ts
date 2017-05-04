import { Injectable } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class TaqtileApiService {
  baseUrl: string;

  constructor(private http: Http) {
    this.baseUrl = 'https://tq-template-node.herokuapp.com';
  }

  login(user: string, password: string): Observable<any> {
    let body = { user, password };
    return this.http.post(`${this.baseUrl}/authenticate`, body)
                    .map(response => response.json());
  }

  fetchUsers(): Observable<any> {
    let headers = new Headers();

    headers.append('Authorization', 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJJZCI6MX0sImlhdCI6MTQ5MzkwNjQwMywiZXhwIjoxNDkzOTEwMDAzfQ.kZqWOGjV5IuJ4WX4zwNnK5JRM3k5RspsFlO5EPlHSP0');

    let params = new URLSearchParams();

    params.append('pagination', JSON.stringify({ window: 10, page: 1 }));

    return this.http.get(`${this.baseUrl}/users`, { params, headers })
                    .map(response => response.json());
  }

}
