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

    public Optional<Course> findByName(String name) {
        List<Course> courses = courseRepository.findByName(name);
        if (courses.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(courses.get(0));
        }
    }

    public List<Course >findByGroup(String group) {
        return courseRepository.findByGroup(group);
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public boolean deleteByName(String name) {
        boolean exists = courseRepository.existsByName(name);
        if (exists) {
            courseRepository.deleteByName(name);
        }
        return exists;
    }

    public boolean create(Course course) {
        boolean exists = courseRepository.existsByName(course.getName());
        if (!exists) {
            courseRepository.save(course);
        }
        return !exists;
    }

    public boolean update(Course cource) {
        Optional<Course> oldCourceOpt = findByName(cource.getName());
        if (oldCourceOpt.isPresent()) {
            Course oldCource = oldCourceOpt.get();

            oldCource.setName(cource.getName());
            oldCource.setTopic(cource.getTopic());
            oldCource.setGroup(cource.getGroup());
            oldCource.setDifficulty(cource.getDifficulty());
            oldCource.setDescription(cource.getDescription());
            oldCource.setPoints_to_get(cource.point_to_get());
            oldCource.setSkills_to_complete(cource.getskills_to_complete());
            courceRepository.save(oldCource);
        }
        return oldCourceOpt.isPresent();
    }


}
