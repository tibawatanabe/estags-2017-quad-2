import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Location } from '@angular/common';

import 'rxjs/add/operator/switchMap';

import { TaqtileApiService } from '../taqtile-api.service';
import { UserInfoService } from '../user-info.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  user;
  id: string;
  errorMessage: string;

  constructor(
    private taqtileApiService: TaqtileApiService,
    private userInfoService: UserInfoService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.getUser(this.id);
  }

  getUser(id: string) {
    this.taqtileApiService.getUser(id)
                            .subscribe(
                              response => this.user = response.data,
                              error => {
                                console.log('Error getting user details');
                                this.errorEdit(error.status);
                              }
                            );
  }

  errorEdit(statusCode: number) {
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

  goBack() {
    this.location.back();
  }

  save(name: string, email: string, id: string) {
    this.taqtileApiService.edit(name, email, id)
      .subscribe(
            response => this.goBack(),
            error => console.log('Error getting user details')
          );
  }
}
