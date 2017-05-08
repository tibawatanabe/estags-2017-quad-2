import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserInfoService } from '../user-info.service';
import { TaqtileApiService } from '../taqtile-api.service';
import { MessagesService } from '../messages.service';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user;
  errorMessage: string;

  constructor(
    private taqtileApiService: TaqtileApiService,
    private userInfoService: UserInfoService,
    private messagesService: MessagesService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getUser();

    this.messagesService.flashMessage$.subscribe((message) => {
      if (message.id == 'users-list') {
        this.errorMessage = message.message;
      }
    })
  }

  getUser() {
    this.user = this.taqtileApiService.getUser(this.userInfoService.getId())
                                        .subscribe(
                                          response => this.user = response.data,
                                          error => {
                                            console.log('Error getting user');
                                            this.messagesService.setMessage('home', error.status);
                                          }
                                        );
  }
}
