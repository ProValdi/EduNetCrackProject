import {Component, EventEmitter, Inject, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Lesson} from "../../model/entity/lesson";
import {LessonService} from "../../services/lesson-service/lesson.service";
import {Tag} from "../../model/entity/tag";
import {TagService} from "../../services/tag-service/tag.service";
import {flatMap} from "rxjs/internal/operators";
import {AppComponent} from "../app/app.component";
import {LearnRequest} from "../../model/entity/learn-request";
import {ModalService} from "../../services/modal-service/modal.service";
import {LearnRequestBody} from "../../model/entity/learn-request-body";
import {LessonBody} from "../../model/entity/lesson-body";
import {DOCUMENT} from "@angular/common";


@Component({
  selector: 'app-teach-tab',
  templateUrl: './teach-tab.component.html',
  styleUrls: ['./teach-tab.component.less']
})
export class TeachTabComponent implements OnInit{

  lessons: Lesson[];
  tags: Map<number, Tag[]> = new Map<number, Tag[]>();
  learnsRequest: LearnRequest[];

  allTags: Tag[] = [];
  possibleTags: Tag[] = [];
  selectedTag: Tag;

  descriptionText: string;
  costNumber: number;
  modalName: string;

  constructor(private lessonService: LessonService,
              private tagService: TagService,
              private modalService: ModalService) {
  
  }
  
  ngOnInit(): void {
    this.lessonService.getByTeacherId(AppComponent.currentUserId).subscribe(lessons => {
      this.lessons = lessons;
      for (let lesson of lessons) {
        this.tagService.findWithParents(lesson.tag.id).subscribe(tags => {
          this.tags.set(lesson.id, tags.reverse());
        })
      }
    });
    this.tagService.getChildren(0).subscribe(tags => this.possibleTags = tags);
    this.allTags = [];
  }

  getByTagId(): void {
    this.lessonService.getByTagId(this.tags[this.allTags.length - 1].id).subscribe(lessons => this.lessons = lessons);
  }

  choseTag(): void {
    if (this.selectedTag != null) {
      this.allTags.push(this.selectedTag);
      this.tagService.getChildren(this.selectedTag.id).subscribe(tags => {
        this.possibleTags = tags;
        if (this.possibleTags.length === 0) {
          this.getByTagId();
        }
      });
    }
  }

  back(): void {
    this.allTags.pop();
    this.tagService.getChildren(this.allTags.length > 0 ? this.tags[this.allTags.length - 1].id : 0)
      .subscribe(tags => this.possibleTags = tags);
  }
  
  
  delete(id: number): void {
    this.lessonService.delete(id).subscribe();
    this.lessons = this.lessons.filter(lesson => lesson.id != id);
  }
  
  openModal(id: string) {
    this.modalName = id;
    this.modalService.open(id);
  }

  closeModal(id: string) {
    this.modalService.close(id);
  }
  
  addLesson(): void {
    if (this.selectedTag == null || this.costNumber == null) {
      alert("no tags selected or no cost number");
      return;
    }
    const lessonBody: LessonBody = new LessonBody();
    lessonBody.description = this.descriptionText;
    lessonBody.pointsToGet = this.costNumber;
    lessonBody.tagId = this.selectedTag.id;
    lessonBody.teacherId = AppComponent.currentUserId;
    this.lessonService.create(lessonBody).subscribe(_ => {
      this.lessonService.getByTeacherId(lessonBody.teacherId).subscribe(lessons => {
        this.lessons = lessons;
        const currentLesson = lessons[lessons.length - 1];
        this.tagService.findWithParents(currentLesson.tag.id).subscribe(tags => {
          this.tags.set(currentLesson.id, tags.reverse());
        })
      });
    });
    this.closeModal(this.modalName);
  }
  
}
