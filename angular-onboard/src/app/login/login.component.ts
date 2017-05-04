import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { UserComponent } from '../user/user.component';
import { TaqtileApiService } from '../taqtile-api.service';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  usuario: string;
  senha: string;
  logou = "nada aqui";
  sub: any;
  
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
    this.senha = senha;

    this._taqtileApiService.login(usuario, senha).subscribe(data => {this.logou = data});

    this.sub = this.route.params.subscribe(params => {
      this._taqtileApiService.login(usuario, senha).subscribe(data => {
        this.logou = data;
      }, error => console.log('Could not load item'));
    });
  }

  

  constructor(
    private _taqtileApiService: TaqtileApiService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
  }

}
