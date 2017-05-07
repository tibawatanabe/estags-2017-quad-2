import { Injectable } from '@angular/core';

@Injectable()
export class UserInfoService {

  constructor() { }

  token: string;

  setToken(token: string) {
    this.token = token;
  }

  getToken() {
    return this.token;
  }

}
