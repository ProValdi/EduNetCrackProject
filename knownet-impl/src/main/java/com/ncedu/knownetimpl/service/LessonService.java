package com.ncedu.knownetimpl.service;
import com.ncedu.knownetimpl.model.LessonBody;
import com.ncedu.knownetimpl.model.entity.Lesson;
import com.ncedu.knownetimpl.model.entity.User;
import com.ncedu.knownetimpl.repository.LessonRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class LessonService {

    private final UserService userService;

    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository, UserService userService) {
        this.lessonRepository = lessonRepository;
        this.userService = userService;
    }
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> findById(Long id) {
        return lessonRepository.findById(id);
    }

    public List<Lesson> findByName(String name) {
        return lessonRepository.findByName(name);
    }

    public List<Lesson> findByTagId(Long tagId) {
        return lessonRepository.findByTagId(tagId);
    }

    public List<Lesson> findByTeacherId(Long teacherId) { return lessonRepository.findByTeacherId(teacherId); }

    public boolean deleteById(Long id) {
        boolean exists = lessonRepository.existsById(id);
        if (exists) {
            lessonRepository.deleteById(id);
        }
        return exists;
    }

    public boolean create(Lesson lesson) {
        boolean exists = lessonRepository.existsById(lesson.getId());
        if (!exists) {
            lessonRepository.save(lesson);
        }
        return !exists;
    }

    public boolean update(Lesson lesson) {
        Optional<Lesson> oldCourseOpt = findById(lesson.getId());
        if (oldCourseOpt.isPresent()) {
            Lesson oldLesson = oldCourseOpt.get();
            oldLesson.setName(lesson.getName());
            oldLesson.setTag(lesson.getTag());
            oldLesson.setTopic(lesson.getTopic());
            oldLesson.setPointsToGet(lesson.getPointsToGet());
            oldLesson.setDifficulty(lesson.getDifficulty());
            oldLesson.setDescription(lesson.getDescription());
            lessonRepository.save(oldLesson);
        }
        return oldCourseOpt.isPresent();
    }

    public Lesson makeFromBody(LessonBody body) {
        Lesson lesson = new Lesson();
        lesson.setId(body.getId());
        Optional<User> teacher = userService.findById(body.getTeacherId());
        lesson.setTeacher(teacher.orElse(new User()));

        return lesson;
    }




}
