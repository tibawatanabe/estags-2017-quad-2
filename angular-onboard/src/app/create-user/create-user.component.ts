import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { TaqtileApiService } from '../taqtile-api.service';
import { UserInfoService } from '../user-info.service';

@Component({
  selector: 'create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  types = ["admin", "manager"]
  errorMessage: string;
  id: string;
  name: string;
  password: string;
  email: string;
  type: string;
  submitted = false;

  constructor(
    private taqtileApiService: TaqtileApiService,
    private userInfoService: UserInfoService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  createUser(name: string, email: string, password: string, type: string) {
    this.submitted = true;
    this.taqtileApiService.createUser(name, email, password, type)
                           .subscribe(
                             response =>{
                               this.id = response.data.id;
                               this.router.navigate(['/user-details', this.id]);
                             },
                             error => {
                               this.submitted = false;
                               console.log('Error creating this user');
                               this.errorCreate(error.status);
                             }
                           );
  }

  errorCreate(statusCode: number) {
    switch(statusCode){
      case 401: {
        this.errorMessage = "Sua seção expirou. Faça login novamente!";
        this.userInfoService.logout();
        this.router.navigate(['/login']);
        this.userInfoService.sendMessage('login', this.errorMessage);
        break;
      }
      case 0: {
        this.errorMessage = "Houve um problema na conexão. Verifique seu acesso à internet e tente novamente";
        break;
      }
      default: {
        this.errorMessage = "Ocorreu algum problema ao criar este usuário. Tente novamente!";
        break;
      }
    }
  }

}
