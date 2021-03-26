import { Component, OnInit } from '@angular/core';
import {LearnRequest} from "../../model/entity/learn-request";
import {Tag} from "../../model/entity/tag";
import {AppComponent} from "../app/app.component";
import {TagService} from "../../services/tag-service/tag.service";
import {LearningRequestService} from "../../services/learning-request-service/learning-request.service";
import {LearnRequestBody} from "../../model/entity/learn-request-body";
import {Status} from "../../model/entity/learn-request-status";

@Component({
  selector: 'app-outgoing-requests',
  templateUrl: './outgoing-requests.component.html',
  styleUrls: ['./outgoing-requests.component.less']
})
export class OutgoingRequestsComponent implements OnInit {
  learns: LearnRequest[];
  tags: Map<number, Tag[]> = new Map<number, Tag[]>();
  Status = Status;

  constructor(private tagService: TagService,
              private learningRequestService: LearningRequestService) {
  }

  ngOnInit(): void {
    this.learningRequestService.getByStudentId(AppComponent.currentUserId).subscribe(learns => {
      this.learns = learns;
      for (let learn of learns) {
        this.tagService.findWithParents(learn.lesson.tag.id).subscribe(tags => {
          this.tags.set(learn.lesson.tag.id, tags.reverse());
        })
      }
    });
  }
  
  withdraw(learn: LearnRequest): void {
    let body: LearnRequestBody = new LearnRequestBody();
    body.id = learn.id;
    body.teacherId = learn.teacher.id;
    body.studentId = learn.student.id;
    body.lessonId = learn.lesson.id;
    body.status = Status.MEETING_CANCELED;
    body.hiddenForTeacher = learn.hiddenForTeacher;
    body.hiddenForStudent = learn.hiddenForStudent;
    this.learningRequestService.update(body).subscribe();
    this.learns = this.learns.filter(lesson => lesson.id !== body.id);
  }
  
  accept(learn: LearnRequest): void {
    let body: LearnRequestBody = new LearnRequestBody();
    body.id = learn.id;
    body.teacherId = learn.teacher.id;
    body.studentId = learn.student.id;
    body.lessonId = learn.lesson.id;
    body.status = Status.MEETING_CONFIRMED;
    body.hiddenForTeacher = learn.hiddenForTeacher;
    body.hiddenForStudent = learn.hiddenForStudent;
    this.learningRequestService.update(body).subscribe();
    this.learns = this.learns.filter(lesson => lesson.id !== body.id);
  }

  deny(learn: LearnRequest): void {
    let body: LearnRequestBody = new LearnRequestBody();
    body.id = learn.id;
    body.teacherId = learn.teacher.id;
    body.studentId = learn.student.id;
    body.lessonId = learn.lesson.id;
    body.status = Status.MEETING_DISPROVED;
    body.hiddenForTeacher = learn.hiddenForTeacher;
    body.hiddenForStudent = learn.hiddenForStudent;
    this.learningRequestService.update(body).subscribe();
    this.learns = this.learns.filter(lesson => lesson.id !== body.id);
  }

  takeAction(learn): void {
    
  }

}
