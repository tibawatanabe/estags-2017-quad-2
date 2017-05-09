import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';

import { TaqtileApiService } from '../taqtile-api.service';

import 'rxjs/add/operator/do';
import { UserInfoService } from "app/user-info.service";

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {
  users;
  page: number;
  errorMessage;

  constructor(
    private taqtileApiService: TaqtileApiService,
    private userInfoService: UserInfoService,
    private router: Router
  ) {}

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.taqtileApiService.getUsers()
      // .do(console.log)
      // .map(response => response.data)
      .subscribe(
        response => this.users = response.data,
        error => {
          console.log('Error getting users');
          this.errorList(error.status);
        }
      );
  }

  errorList(statusCode: number) {
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

  gotoDetails(id: number) {
    this.router.navigate(['/user-details', id]);
  }
}