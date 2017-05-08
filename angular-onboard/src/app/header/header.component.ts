import { Component, OnInit } from '@angular/core';

import { UserInfoService } from '../user-info.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLoggedIn = true;

  constructor(private userInfoService: UserInfoService) { }

  ngOnInit() {
  }

  logout() {
    this.userInfoService.logout();
  }
}
