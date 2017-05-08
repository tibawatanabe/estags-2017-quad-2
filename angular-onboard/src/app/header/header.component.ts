import { Component, OnInit } from '@angular/core';

import { UserInfoService } from '../user-info.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  token;
  isLoggedIn = false;

  constructor(private userInfoService: UserInfoService) { }

  ngOnInit() {
    this.userInfoService.isLoggedIn$.subscribe(response => {
      if (response.token) {
        this.isLoggedIn = true;
      } else {
        this.isLoggedIn = false;
      }
    });
  }

  logout() {
    this.userInfoService.logout();
  }
}
