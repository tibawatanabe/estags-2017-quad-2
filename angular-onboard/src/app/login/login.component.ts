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
  usuario: string;
  senha: string;
  token: string;

  constructor(
    private _taqtileApiService: TaqtileApiService,
    private _userInfoService: UserInfoService
  ) { }

  ngOnInit() {
  }
  
  model = new UserComponent(0, "", "", "");
  submitted = false;
  onSubmit() { 
    this.submitted = true;
  }

  login(usuario: string, senha: string) {
    this.usuario = usuario;
    this.senha = senha;

    this._taqtileApiService.login(usuario, senha).subscribe(response => {this.token = response.data.token});
    
    this._userInfoService.setToken(this.token);
  }
}
