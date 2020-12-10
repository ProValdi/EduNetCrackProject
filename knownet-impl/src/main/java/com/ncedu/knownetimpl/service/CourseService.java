package com.ncedu.knownetimpl.service;

import com.ncedu.knownetimpl.model.Course;
import com.ncedu.knownetimpl.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }




    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public boolean deleteById(Long id) {
        boolean exists = courseRepository.existsById(id);
        if (exists) {
            courseRepository.deleteById(id);
        }
        return exists;
    }

    public boolean create(Course course) {
        boolean exists = courseRepository.existsById(course.getId());
        if (!exists) {
            courseRepository.save(course);
        }
        return !exists;
    }

    public boolean update(Course course) {
        Optional<Course> oldCourseOpt = findById(course.getId());
        if (oldCourseOpt.isPresent()) {
            Course oldCourse = oldCourseOpt.get();

            oldCourse.setName(course.getName());
            oldCourse.setTopic(course.getTopic());
            oldCourse.setDifficulty(course.getDifficulty());
            oldCourse.setDescription(course.getDescription());
            courseRepository.save(oldCourse);
        }
        return oldCourseOpt.isPresent();
    }


}
