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

  onSubmit(user: string, password: string) {
    this.submitted = true;

    this.taqtileApiService.login(user, password)
      .map(response => response.data.token)
      .subscribe(
        token => this.userInfoService.setToken(token),
        error => console.log('Error logging this user')
      );
  }
}
