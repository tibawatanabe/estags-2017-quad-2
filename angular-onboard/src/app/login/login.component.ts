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
    private _taqtileApiService: TaqtileApiService,
    private _userInfoService: UserInfoService
  ) { }

  ngOnInit() {
  }

  onSubmit(usuario: string, senha: string) {
    this._taqtileApiService.login(usuario, senha).subscribe(response => {
                                                            this.token = response.data.token;
                                                            this._userInfoService.setToken(this.token);},
                                                            error => console.log('Error logging this user'));
    this.submitted = true;
  }
}
