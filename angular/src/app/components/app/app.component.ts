import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {
  public static currentUserId: number = 2;
  public static currentUserLogin: string = "masha";
  public static currentUserPassword: string = "0000";
  public static isAdmin: boolean = true;
  _isAdmin: boolean = AppComponent.isAdmin;

  constructor(public router: Router ) {}
}
