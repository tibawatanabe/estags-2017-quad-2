import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UserInfoService } from './user-info.service';
import { TaqtileApiService } from './taqtile-api.service';
import { Observable } from "rxjs/Observable";

@Injectable()
export class LoginGuardService implements CanActivate {
  isLogged = false;

  constructor (
    private taqtileApiService: TaqtileApiService,
    private userInfoService: UserInfoService,
    private router: Router
  ) { }

  canActivate() {
    if (this.userInfoService.getToken()) {
      return true;
    }

    this.userInfoService.sendMessage('login', 'Faça login');
    this.router.navigate(['/login']);
    return false;
  }

  // store the URL so we can redirect after logging in
  redirectUrl: string;


}
