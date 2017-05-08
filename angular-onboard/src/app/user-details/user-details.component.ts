import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Location } from '@angular/common';
import 'rxjs/add/operator/switchMap';


import { TaqtileApiService } from '../taqtile-api.service';
import { UserInfoService } from '../user-info.service';

@Component({
  selector: 'user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  user;
  errorMessage: string;
  id: string;

  constructor(
    private taqtileApiService: TaqtileApiService,
    private userInfoService: UserInfoService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.getUser(this.id);
  }

  getUser(id: string) {
    this.taqtileApiService.getUser(id)
                            .subscribe(
                              response => this.user = response.data,
                              error => {
                                console.log('Error getting user details');
                                this.errorDetails(error.status);
                              }
                            );
  }

  errorDetails(statusCode: number) {
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
        this.errorMessage = "Ocorreu algum problema ao mostrar os detalhes do usuário. Tente novamente!";
        break;
      }
    }
  }

  goBack(): void {
    this.location.back();
  }

  gotoList() {
    this.router.navigate(['users-list']);
  }

  gotoEdit(id: number): void {
    this.router.navigate(['/edit-user', id]);
  }

}
