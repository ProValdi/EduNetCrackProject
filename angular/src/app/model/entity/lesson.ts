import {User} from "./user";
import {Tag} from "./tag";

export class Lesson {
  id: number;
  teacher: User;
  name: string;
  tag: Tag;
  topic: string;
  difficulty: number;
  pointsToGet: number;
  description: string;
  skillsToComplete: string;
}
