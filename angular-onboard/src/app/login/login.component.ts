import { Component, OnInit } from '@angular/core';

import { UserComponent } from '../user/user.component'

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  usuario: string;
  senha: string;
  
  model = new UserComponent(0, "", "", "");
  submitted = false;
  onSubmit() { this.submitted = true; }
  // TODO: Remove this when we're done
  get diagnostic() { return JSON.stringify(this.model); }

  newUser() {
    this.model = new UserComponent(42, '', '', '');
  }

  setAuth(usuario: string, senha: string) {
    this.usuario = usuario;
    this.senha = usuario;
  }

  

  constructor() { }

  ngOnInit() {
  }

}
