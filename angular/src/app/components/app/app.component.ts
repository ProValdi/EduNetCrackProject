import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from "../../services/user-service/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent implements OnInit{
  public static currentUserId: number = 2;
  public static currentUserLogin: string = "masha";
  public static currentUserPassword: string = "0000";
  public static currentUserPoints: number;
  public static isAdmin: boolean = true;
  _isAdmin: boolean = AppComponent.isAdmin;
  

  
  
  /*
  todo
    каталог пользователей: удаление (удалить, прочекать, что удалился)  
    3) новые значения БД
    2) свитчинг поинтов
   */
  
  constructor(public router: Router,
              private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getById(AppComponent.currentUserId).subscribe(user => {
      this._isAdmin = (user.role == "ADMIN");
      AppComponent.currentUserPoints = user.points;
    });
  }
  
}
