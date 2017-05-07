import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { UserComponent } from '../user/user.component';
import { TaqtileApiService } from '../taqtile-api.service';
import { UserInfoService } from '../user-info.service';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  token = null;
  campoVazioUser = null;
  campoVazioPassword = null;
  submitted = false;
  errorMessage: string;

  constructor(
    private taqtileApiService: TaqtileApiService,
    private userInfoService: UserInfoService
  ) { }

  ngOnInit() {
  }

  login(user: string, password: string) {
    this.taqtileApiService.login(user, password)
      .map(response => response.data.token)
      .subscribe(
        token => {
          this.userInfoService.setToken(token);
          this.submitted = true;
        },
        error => {
          console.log('Error logging this user');
          this.errorLogin(error.status);
        }
      );
  }

  errorLogin(statusCode: number) {
    this.submitted = false;

    switch(statusCode){
      case 401: {
        this.errorMessage = "Usuário ou senha inválidos. Tente novamente!";
        break;
      }
      case 0: {
        this.errorMessage = "Houve um problema na conexão. Verifique seu acesso à internet";
        break;
      }
      default: {
        this.errorMessage = "Ocorreu algum problema ao fazer seu login. Tente novamente!";
        break;
      }
    }
  }
}
