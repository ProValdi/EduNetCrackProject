import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {
  public static currentUserId: number;
  public static currentUserLogin: string;
  public static currentUserPassword: string;

  constructor(public router: Router ) {}
}
