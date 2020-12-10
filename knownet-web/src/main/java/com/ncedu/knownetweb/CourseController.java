package com.ncedu.knownetweb;

import com.ncedu.knownetimpl.model.Course;
import com.ncedu.knownetimpl.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;





@Slf4j
@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> findAll() {
        log.debug("requested: courses get    (all)");
        return ResponseEntity.ok().body(courseService.findAll());
    }

    @GetMapping(value = "byId/{id}")
    public ResponseEntity<Course> findById(@PathVariable("id") Long id) {
        log.debug("requested: course  get    (id = {})", id);
        Optional<Course> course = courseService.findById(id);
        return ResponseEntity.of(course);
    }


    @DeleteMapping(value = "byId/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        log.debug("requested: course  delete (id = {})", id);
        boolean deleted = courseService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok().body("course with id = " + id + " was deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("course with id = " + id + " does not exist");
        }
    }

    @PostMapping(value = "course")
    public ResponseEntity<String> create(@RequestBody Course course) {
        Long id = course.getId();
        log.debug("requested: course  create (id = {})", id);
        boolean created = courseService.create(course);
        if (created) {
            return ResponseEntity.ok().body("course with id = " + id + " was created");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("course with id = " + id + " already exists");
        }
    }

    @PutMapping(value = "course")
    public ResponseEntity<String> update(@RequestBody Course course) {
        Long id = course.getId();
        log.debug("requested: course  update (id = {})", id);
        boolean updated = courseService.update(course);
        if (updated) {
            return ResponseEntity.ok().body("course with id = " + id + " was updated");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("course with id = " + id + " does not exist");
        }
    }


}






