import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {catchError, tap} from 'rxjs/operators';
import {ErrorHandler} from '../../services/error-handler';

import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../model/entity/user';
import {UserService} from '../../services/user-service/user.service';
import {AppComponent} from '../app/app.component';

@Component({
  selector: 'app-sign-in-tab',
  templateUrl: './sign-in-tab.component.html',
  styleUrls: ['./sign-in-tab.component.less']
})
export class SignInTabComponent implements OnInit {

  protected url = 'http://localhost:8083';

  constructor(
    public router: Router, private http: HttpClient, private userService: UserService
  ) {}

  ngOnInit(): void {
  }

  login(login, password): void {
    this.userService.getAuth(login, password).subscribe(
      (response: HttpResponse<User>) => {
        switch (response.status) {
          // todo remove from here, add this check to other places
          // кста оно почему-то не работает)
          case 200:
            const user = response.body;
            user.password = password;
            AppComponent.currentUserId = user.id;
            AppComponent.currentUserLogin = login;
            AppComponent.currentUserPassword = password;
            console.log('auth');
            break;
          case 401:
            console.log('not authenticated');
            break;
          case 403:
            console.log('forbidden');
            break;

        }
      }
    );
  }

  registry(login, password, passwordConfirmation): void {
    if (password !== passwordConfirmation) {
      console.log('confirmation is not equal to password');
      return;
    }
    const user: User = new User();
    user.password = password;
    user.login = login;
    this.userService.createWithResponse(user).subscribe(
      response => {
        console.log('registered');
        console.log(response);
      },
      // todo я не знаю как сделать, чтобы он читал строку, а не json. из-за этого выбивается ошибка даже при 200-ОК
      error => {
        switch (error.status) {
          case 200:
            console.log('registered');
            break;
          case 400:
            console.log(error.error);
            break;
          default:
            console.log('unexpected http response code ' + error.status);

        }
      }
    );
  }

}
