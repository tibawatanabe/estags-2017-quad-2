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
    let body = { user, password };
    return this.http.post(`${this.baseUrl}/authenticate`, body)
                    .map(response => response.json());
  }

}
