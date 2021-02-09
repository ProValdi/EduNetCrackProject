import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../../model/entity/user';
import {BaseService} from "../base-service/base.service";

@Injectable({
  providedIn: 'root'
})
export class UserService extends BaseService<User, User>{

  constructor(protected http: HttpClient) {
    super(http, User);
    this.url += '/users';
  }

}
