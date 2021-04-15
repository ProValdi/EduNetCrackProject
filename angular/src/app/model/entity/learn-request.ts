import {User} from "./user";
import {Lesson} from "./lesson";
import {Status} from "./learn-request-status";

export class LearnRequest {
  id: number;
  teacher: User;
  student: User;
  lesson: Lesson;
  status: Status;
  hiddenForTeacher: boolean;
  hiddenForStudent: boolean;
}
