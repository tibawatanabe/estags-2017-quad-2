import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { TaqtileApiService } from '../taqtile-api.service';

import 'rxjs/add/operator/do';
import { UserInfoService } from "app/user-info.service";
import { MessagesService } from '../messages.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {
  users;
  size: number = 5
  page: number = 1;
  sizeOptions = [5, 10, 15, 20];
  errorMessage;
  firstPage = true;
  lastPage = false;
  totalPages: number;
  listStart: number;

  constructor(
    private taqtileApiService: TaqtileApiService,
    private userInfoService: UserInfoService,
    private messagesService: MessagesService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.getUsers();

    this.messagesService.flashMessage$.subscribe((message) => {
      if (message.id == 'users-list') {
        this.errorMessage = message.message;
      }
    })
  }

  getUsers() {
    this.route.params.switchMap((params: Params) => this.taqtileApiService.getUsers(this.size, params['page']))
      // .do(console.log)
      // .map(response => response.data)
      .subscribe(
        response => {
          this.users = response.data;
          this.totalPages = response.pagination.totalPages;
          this.listStart = 1 + (this.page-1)*this.size;
          if(response.pagination.totalPages == 1) {
            this.lastPage = true;
          }
        },
        error => {
          console.log('Error getting users');
          this.messagesService.setMessage('users-list', error.status);
        }
      );
  }

  gotoDetails(id: number) {
    this.router.navigate(['/user-details', id]);
  }

  previousPage() {
    this.page = this.page - 1;
    this.lastPage = false;

    if (this.page == 1) {
      this.firstPage = true;
    }

    this.router.navigate(['users-list', this.page]);
  }

  nextPage() {
    this.page = this.page + 1;
    this.firstPage = false;

    if (this.page == this.totalPages) {
      this.lastPage = true;
    }

    this.router.navigate(['users-list', this.page]);
  }
}