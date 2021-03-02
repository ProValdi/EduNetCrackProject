import { Component, OnInit } from '@angular/core';
import { User } from "../../model/entity/user";
import { UserService } from "../../services/user-service/user.service";
import { AppComponent} from "../app/app.component";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.less']
})
export class UserPageComponent implements OnInit {

  user: User;
  
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getById(AppComponent.currentUserId)
      .subscribe(user => this.user = user);
  }
}
