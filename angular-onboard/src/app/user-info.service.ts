import { Injectable } from '@angular/core';
import { ReplaySubject} from 'rxjs/ReplaySubject';


@Injectable()
export class UserInfoService {
  id: string;
  token: string;

  private flashMessageSource = new ReplaySubject<any>(1);

  flashMessage$ = this.flashMessageSource.asObservable();

  setId(id: string) {
    this.id = id;
  }

  getId() {
    return this.id;
  }

  setToken(token: string) {
    this.token = token;
  }

  getToken() {
    return this.token;
  }

  logout() {
    this.token = null;
    this.id = null;
  }

  sendMessage(id: string, message: string) {
    this.flashMessageSource.next({id, message});
  }

}
