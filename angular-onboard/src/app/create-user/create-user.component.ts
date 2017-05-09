import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { TaqtileApiService } from '../taqtile-api.service';
import { UserInfoService } from '../user-info.service';

@Component({
  selector: 'create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  types = ["admin", "manager"]
  successMessage: string;
  id: string;
  campoVazioNome;
  campoVazioSenha;
  campoVazioEmail;
  campoVazioTipo;

  constructor(
    private taqtileApiService: TaqtileApiService
  ) { }

  ngOnInit() {
  }

  onSubmit(name: string, email: string, password: string, type: string) {
    this.taqtileApiService.createUser(name, email, password, type)
                           .subscribe(
                             response =>{
                              this.id = response.data.id;
                              this.successMessage = "Usuário criado com sucesso!" 
                             },
                             error => console.log('Error creating this user')
                           );
  }

}
