import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class TaqtileApiService {
  baseUrl: string;

  constructor(private http: Http) {
    this.baseUrl = 'https://tq-template-node.herokuapp.com';
  }

  login(user: string, password: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/authenticate`, `{ "user": ${user}, "password": ${password }`)
                    .map(response => response.json());
  }

}
