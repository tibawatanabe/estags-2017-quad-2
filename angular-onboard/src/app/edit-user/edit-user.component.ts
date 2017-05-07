import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import 'rxjs/add/operator/switchMap';

import { TaqtileApiService } from '../taqtile-api.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  user;

  constructor(
    private taqtileApiService: TaqtileApiService,
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.taqtileApiService.getUser(params['id']))
      .subscribe(
        response => this.user = response.data,
        error => console.log('Error getting user details')
      );
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
