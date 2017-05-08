import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { TaqtileApiService } from '../taqtile-api.service';
import { UserInfoService } from '../user-info.service';
import { MessagesService } from '../messages.service';

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
    private messagesService: MessagesService,
    private router: Router
  ) { }

  ngOnInit() {
    this.messagesService.flashMessage$.subscribe((message) => {
      if (message.id == 'create-user') {
        this.errorMessage = message.message;
      }
    })
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
                               this.messagesService.setMessage('create-user', error.status);
                             }
                           );
  }
}
