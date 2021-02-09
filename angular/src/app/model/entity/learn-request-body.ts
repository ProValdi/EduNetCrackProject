import {Status} from "./learn-request-status";

export class LearnRequestBody {
  id: number;
  teacherId: number;
  studentId: number;
  lessonId: number;
  status: Status;
  hiddenForTeacher: boolean;
  hiddenForStudent: boolean;
}
