import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Location } from '@angular/common';

import 'rxjs/add/operator/switchMap';

import { TaqtileApiService } from '../taqtile-api.service';
import { UserInfoService } from '../user-info.service';
import { MessagesService } from '../messages.service';

@Component({
  selector: 'edit-user',
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
    private messagesService: MessagesService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.getUser(this.id);

    this.messagesService.flashMessage$.subscribe((message) => {
      if (message.id == 'edit-user') {
        this.errorMessage = message.message;
      }
    })
  }

  getUser(id: string) {
    this.taqtileApiService.getUser(id)
                            .subscribe(
                              response => this.user = response.data,
                              error => {
                                console.log('Error getting user details');
                                this.messagesService.setMessage('edit-user', error.status);
                              }
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
