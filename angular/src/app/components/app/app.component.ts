import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {
  public static currentUserId: number = 2;
  public static currentUserLogin: string = "admin";
  public static currentUserPassword: string = "user";
  public static isAdmin: boolean = false;
  _isAdmin: boolean = AppComponent.isAdmin;

  constructor(public router: Router ) {}
}
