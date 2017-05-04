import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(
    public id: number,
    public name: string,
    public email: string,
    public type: string
  ) { }

  ngOnInit() {
  }

}
