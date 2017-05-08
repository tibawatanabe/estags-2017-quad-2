import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserInfoService } from '../user-info.service';
import { TaqtileApiService } from '../taqtile-api.service';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user;
  errorMessage: string;

  constructor(
    private taqtileApiService: TaqtileApiService,
    private userInfoService: UserInfoService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getUser();
  }

  getUser() {
    this.user = this.taqtileApiService.getUser(this.userInfoService.getId())
                                        .subscribe(
                                          response => this.user = response.data,
                                          error => {
                                            console.log('Error getting user');
                                            this.errorHome(error.status);
                                          }
                                        );
  }

  errorHome(statusCode: number) {
    switch(statusCode){
      case 401: {
        this.errorMessage = "Sua seção expirou. Faça login novamente!";
        this.userInfoService.logout();
        this.router.navigate(['/login']);
        this.userInfoService.sendMessage('login', this.errorMessage);
        break;
      }
      case 0: {
        this.errorMessage = "Houve um problema na conexão. Verifique seu acesso à internet";
        break;
      }
      default: {
        this.errorMessage = "Ocorreu algum problema ao listar os usuários. Tente novamente!";
        break;
      }
    }
  }

}
