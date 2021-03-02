import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Lesson} from "../../model/entity/lesson";
import {BaseService} from "../base-service/base.service";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {ErrorHandler} from "../error-handler";
import {LessonBody} from "../../model/entity/lesson-body";

@Injectable({
  providedIn: 'root'
})
export class LessonService extends BaseService<Lesson, LessonBody>{

  constructor(protected http: HttpClient) {
    super(http, Lesson);
    this.url += '/lessons';
  }
  
  getByTagId(id: number): Observable<Lesson[]> {
    return this.http.get<Lesson[]>(this.url + '/byTagId/' + id)
      .pipe(
        catchError(ErrorHandler.handleError<Lesson[]>('getLessons ' + `ByTagId id=${id}`, []))
      );
  }

}
