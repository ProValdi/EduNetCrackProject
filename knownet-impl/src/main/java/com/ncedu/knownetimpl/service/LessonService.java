package com.ncedu.knownetimpl.service;

import com.ncedu.knownetimpl.model.Lesson;
import com.ncedu.knownetimpl.model.Tag;
import com.ncedu.knownetimpl.model.entity.LearnRequest;
import com.ncedu.knownetimpl.repository.LessonRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
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
            oldLesson.setStudent(lesson.getStudent());
            oldLesson.setTeacher(lesson.getTeacher());
            oldLesson.setName(lesson.getName());
            oldLesson.setTopic(lesson.getTopic());
            oldLesson.setPoints_to_get(lesson.getPoints_to_get());
            oldLesson.setDifficulty(lesson.getDifficulty());
            oldLesson.setDescription(lesson.getDescription());
            lessonRepository.save(oldLesson);
        }
        return oldCourseOpt.isPresent();
    }





}
