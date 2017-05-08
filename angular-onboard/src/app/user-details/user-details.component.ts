import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Location } from '@angular/common';
import 'rxjs/add/operator/switchMap';


import { TaqtileApiService } from '../taqtile-api.service';
import { UserInfoService } from '../user-info.service';
import { MessagesService } from '../messages.service';

@Component({
  selector: 'user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  user;
  errorMessage: string;
  id: string;

  constructor(
    private taqtileApiService: TaqtileApiService,
    private userInfoService: UserInfoService,
    private messagesService: MessagesService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.getUser(this.id);

    this.messagesService.flashMessage$.subscribe((message) => {
      if (message.id == 'users-list') {
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
                                this.messagesService.setMessage('user-details', error.status);
                              }
                            );
  }

  goBack(): void {
    this.location.back();
  }

  gotoList() {
    this.router.navigate(['users-list', 1]);
  }

  gotoEdit(id: number): void {
    this.router.navigate(['/edit-user', id]);
  }

}
