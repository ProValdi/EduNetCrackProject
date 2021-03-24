import { Component, OnInit } from '@angular/core';
import {Lesson} from "../../model/entity/lesson";
import {LessonService} from "../../services/lesson-service/lesson.service";
import {Tag} from "../../model/entity/tag";
import {TagService} from "../../services/tag-service/tag.service";
import {LearningRequestService} from "../../services/learning-request-service/learning-request.service";
import {AppComponent} from "../app/app.component";
import {LearnRequestBody} from "../../model/entity/learn-request-body";
import {LearnRequest} from "../../model/entity/learn-request";

@Component({
  selector: 'app-learn-tab',
  templateUrl: './learn-tab.component.html',
  styleUrls: ['./learn-tab.component.less']
})
export class LearnTabComponent implements OnInit {
  
  lessons: Lesson[];
  tags: Tag[];
  allTags: Map<number, Tag[]> = new Map<number, Tag[]>();
  possibleTags: Tag[] = [];
  selectedTag: Tag;
  
  variance: boolean = true;

  constructor(private lessonService: LessonService,
              private tagService: TagService,
              private learningRequestService: LearningRequestService) { }

  ngOnInit(): void {
    this.lessonService.getAll().subscribe(lessons => {
      this.lessons = lessons;
      for (let lesson of lessons) {
        this.tagService.findWithParents(lesson.tag.id).subscribe(tags => {
          this.allTags.set(lesson.id, tags.reverse());
        })
      }
    });
    this.tagService.getChildren(0).subscribe(tags => {
      this.possibleTags = tags;
    });
    this.tags = [];
  }

  getByTagId(): void {
    this.lessonService.getByTagId(this.tags[this.tags.length - 1].id).subscribe(lessons => this.lessons = lessons);
  }

  choseTag(): void {
    this.variance = false;
    if (this.selectedTag != null) {
      this.tags.push(this.selectedTag);
      this.tagService.getChildren(this.selectedTag.id).subscribe(tags => {
        this.possibleTags = tags;
        if (this.possibleTags.length === 0) {
          this.getByTagId();
        }
      });
      this.selectedTag = null;
    }
  }

  back(): void {
    this.tags.pop();
    this.tagService.getChildren(this.tags.length > 0 ? this.tags[this.tags.length - 1].id : 0)
      .subscribe(tags => this.possibleTags = tags);
  }

  requestLesson(lesson: Lesson): void {
    const request: LearnRequestBody = new LearnRequestBody();
    request.lessonId = lesson.id;
    request.studentId = AppComponent.currentUserId;
    this.learningRequestService.create(request).subscribe();
  }

}
