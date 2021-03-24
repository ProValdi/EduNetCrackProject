import {Component, OnInit} from '@angular/core';
import {Tag} from "../../model/entity/tag";
import {TagService} from "../../services/tag-service/tag.service";
import {LearnRequest} from "../../model/entity/learn-request";
import {LearningRequestService} from "../../services/learning-request-service/learning-request.service";
import {AppComponent} from "../app/app.component";
import {Status} from "../../model/entity/learn-request-status";
import {LearnRequestBody} from "../../model/entity/learn-request-body";

@Component({
  selector: 'app-incoming-requests',
  templateUrl: './incoming-requests.component.html',
  styleUrls: ['./incoming-requests.component.less']
})
export class IncomingRequestsComponent implements OnInit {
  learns: LearnRequest[];
  tags: Map<number, Tag[]> = new Map<number, Tag[]>();
  Status = Status;

  constructor(private tagService: TagService, 
              private learningRequestService: LearningRequestService){ }

  ngOnInit(): void {
    this.learningRequestService.getByTeacherId(AppComponent.currentUserId).subscribe(learns => {
      this.learns = learns;
      for (let learn of learns) {
        this.tagService.findWithParents(learn.lesson.tag.id).subscribe(tags => {
          this.tags.set(learn.lesson.tag.id, tags.reverse());
        })
      }
    });
  }
    
  accept(learn: LearnRequest): void {
    let body: LearnRequestBody = new LearnRequestBody();
    body.id = learn.id;
    body.teacherId = learn.teacher.id;
    body.studentId = learn.student.id;
    body.lessonId = learn.lesson.id;
    body.status = Status.LESSON_REQUEST_ACCEPTED;
    body.hiddenForTeacher = learn.hiddenForTeacher;
    body.hiddenForStudent = learn.hiddenForStudent;
    this.learningRequestService.update(body).subscribe();
  }
  
  deny(learn: LearnRequest): void {
    let body: LearnRequestBody = new LearnRequestBody();
    body.id = learn.id;
    body.teacherId = learn.teacher.id;
    body.studentId = learn.student.id;
    body.lessonId = learn.lesson.id;
    body.status = Status.LESSON_REQUEST_REJECTED;
    body.hiddenForTeacher = learn.hiddenForTeacher;
    body.hiddenForStudent = learn.hiddenForStudent;
    this.learningRequestService.update(body).subscribe();
    this.learns = this.learns.filter(lesson => lesson.id !== body.id);
  }
}
