import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {
  public static currentUserId: number = 1;
  public static currentUserLogin: string = "user";
  public static currentUserPassword: string = "user";

  constructor(public router: Router ) {}
}