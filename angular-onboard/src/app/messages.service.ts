import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ReplaySubject } from 'rxjs/ReplaySubject';
import { UserInfoService } from './user-info.service';

@Injectable()
export class MessagesService {
  constructor(
    private userInfoService: UserInfoService,
    private router: Router
  ) {}

  private flashMessageSource = new ReplaySubject<any>(1);
  flashMessage$ = this.flashMessageSource.asObservable();

  errorMessage: string;

  sendMessage(id: string, message: string) {
    this.flashMessageSource.next({id, message});
  }

  setMessage(id: string, statusCode: number) {
    switch(statusCode){
      case 401: {
        this.errorMessage = "Sua seção expirou. Faça login novamente!";
        this.userInfoService.logout();
        this.router.navigate(['/login']);
        this.sendMessage('login', this.errorMessage);
        break;
      }
      case 0: {
        this.errorMessage = "Houve um problema na conexão. Verifique seu acesso à internet";
        this.sendMessage(id, this.errorMessage);
        break;
      }
      default: {
        this.errorMessage = "Ocorreu algum problema ao listar os usuários. Tente novamente!";
        this.sendMessage(id, this.errorMessage);
        break;
      }
    }
  }

}
