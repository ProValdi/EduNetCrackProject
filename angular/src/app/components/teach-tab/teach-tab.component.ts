import { Component, OnInit } from '@angular/core';
import {Lesson} from "../../model/entity/lesson";
import {LessonService} from "../../services/lesson-service/lesson.service";
import {Tag} from "../../model/entity/tag";
import {TagService} from "../../services/tag-service/tag.service";
import {flatMap} from "rxjs/internal/operators";
import {AppComponent} from "../app/app.component";


@Component({
  selector: 'app-teach-tab',
  templateUrl: './teach-tab.component.html',
  styleUrls: ['./teach-tab.component.less']
})
export class TeachTabComponent implements OnInit {

  lessons: Lesson[];
  tags: Map<number, Tag[]> = new Map<number, Tag[]>();
  
  constructor(private lessonService: LessonService,
              private tagService: TagService) { }

  ngOnInit(): void {
    this.lessonService.getByTeacherId(AppComponent.currentUserId).subscribe(lessons => {
      this.lessons = lessons;
      for (let lesson of lessons) {
        this.tagService.findWithParents(lesson.tag.id).subscribe(tags => {
          this.tags.set(lesson.id, tags);
        })
      }
    });
  }
  
  delete(id: number): void {
    this.lessonService.delete(id).subscribe();
    this.lessons = this.lessons.filter(lesson => lesson.id != id);
  }
  

}
