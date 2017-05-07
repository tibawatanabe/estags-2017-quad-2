import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';
import 'rxjs/add/operator/switchMap';


import { TaqtileApiService } from '../taqtile-api.service';

@Component({
  selector: 'user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  user;

  constructor(
    private _taqtileApiService: TaqtileApiService,
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit(): void {
    
    this.route.params
      .switchMap((params: Params) => this._taqtileApiService.getUser(params['id']))
      .subscribe(response => this.user = response.data,
                 error => console.log('Error getting user details'));
  }

  goBack(): void {
    this.location.back();
  }

}
