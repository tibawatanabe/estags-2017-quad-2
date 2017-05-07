import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';

import { TaqtileApiService } from '../taqtile-api.service';

import 'rxjs/add/operator/do';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {
  users;

  constructor(
    private taqtileApiService: TaqtileApiService,
    private router: Router,
  ) {}

  ngOnInit() {
    this.taqtileApiService.getUsers()
                    // .do(console.log)
                    // .map(response => response.data)
                    .subscribe(
                      // users => this.users = users,
                      response => this.users = response.data,
                      error => console.log('Error getting users'));
  }

  gotoDetails(id: number) {
    this.router.navigate(['/user-details', id]);
  }

}