import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

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
  loginNeeded: string;

  constructor(
    private taqtileApiService: TaqtileApiService,
    private userInfoService: UserInfoService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    // this.loginNeeded = this.route.snapshot.params['error'];
    // this.route.params.switchMap((params: Params) => this.loginNeeded = params['error']);

    this.userInfoService.flashMessage$.subscribe((message) => {
      if (message.id == 'login') {
        this.loginNeeded = message.message;
      }
    })
  }

  login(user: string, password: string) {
    this.taqtileApiService.login(user, password)
      .map(response => response.data)
      .subscribe(
        response => {
          this.userInfoService.setToken(response.token);
          this.userInfoService.setId(response.user.id);
          this.submitted = true;
          this.router.navigate(['/home']);
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
        this.campoVazioPassword = null;
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
