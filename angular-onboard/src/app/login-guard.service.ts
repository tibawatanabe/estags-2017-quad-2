import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UserInfoService } from './user-info.service';
import { TaqtileApiService } from './taqtile-api.service';
import { Observable } from "rxjs/Observable";
import { MessagesService } from './messages.service';

@Injectable()
export class LoginGuardService implements CanActivate {

  constructor (
    private taqtileApiService: TaqtileApiService,
    private userInfoService: UserInfoService,
    private messagesService: MessagesService,
    private router: Router
  ) { }

  canActivate() {
    if (this.userInfoService.getToken()) {
      return true;
    }

    this.messagesService.sendMessage('login', 'Faça login');
    this.router.navigate(['/login']);
    return false;
  }
}
