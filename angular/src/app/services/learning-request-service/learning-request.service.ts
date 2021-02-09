import { Injectable } from '@angular/core';
import {BaseService} from "../base-service/base.service";
import {HttpClient} from "@angular/common/http";
import {LearnRequest} from "../../model/entity/learn-request";
import {LearnRequestBody} from "../../model/entity/learn-request-body";

@Injectable({
  providedIn: 'root'
})
export class LearningRequestService extends BaseService<LearnRequest, LearnRequestBody>{

  constructor(protected http: HttpClient) {
    super(http, LearnRequest);
    this.url += '/requests';
    console.log(this.url);
  }

}
