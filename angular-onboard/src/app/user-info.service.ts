import { Injectable } from '@angular/core';
import { ReplaySubject} from 'rxjs/ReplaySubject';

@Injectable()
export class UserInfoService {
  id: string = null;
  token: string = null;

  private isLoggedIn = new ReplaySubject<any>(1);

  isLoggedIn$ = this.isLoggedIn.asObservable();

  setId(id: string) {
    this.id = id;
  }

  getId() {
    return this.id;
  }

  setToken(token: string) {
    this.token = token;
    this.isLoggedIn.next({token});
  }

  getToken() {
    return this.token;
  }

  logout() {
    this.token = null;
    this.id = null;
    this.isLoggedIn.next({token: this.token})
  }
}
