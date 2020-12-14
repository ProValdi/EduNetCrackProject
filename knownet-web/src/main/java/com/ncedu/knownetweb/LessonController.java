package com.ncedu.knownetweb;

import com.ncedu.knownetimpl.model.Lesson;
import com.ncedu.knownetimpl.model.entity.LearnRequest;
import com.ncedu.knownetimpl.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;





@Slf4j
@RestController
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Lesson>> findAll() {
        log.debug("requested: lessons get    (all)");
        return ResponseEntity.ok().body(lessonService.findAll());
    }

    @GetMapping(value = "byId/{id}")
    public ResponseEntity<Lesson> findById(@PathVariable("id") Long id) {
        log.debug("requested: lesson  get    (id = {})", id);
        Optional<Lesson> lesson = lessonService.findById(id);
        return ResponseEntity.of(lesson);
    }

    @GetMapping("byName/{name}")
    public ResponseEntity<List<Lesson>> findByName(@PathVariable(name = "name") String name) {
        log.debug("requested: lesson get    (name = {})", name);
        return ResponseEntity.ok().body(lessonService.findByName(name));
    }



    @GetMapping("byTagId/{TagId}")
    public ResponseEntity<List<Lesson>> findByTagId(@PathVariable(name = "tagId") Long tagId) {
        log.debug("requested: lesson get    (tagId = {})", tagId);
        return ResponseEntity.ok().body(lessonService.findByTagId(tagId));
    }





    @GetMapping("byTeacherId/{teacherId}")
    public ResponseEntity<List<Lesson>> findByTeacherId(
            @PathVariable(name = "teacherId") Long teacherId,
            @RequestParam(name = "activeOnly", defaultValue = "true", required = false) Boolean activeOnly
    ) {
        log.debug("requested: learnRequests get    (teacherId = {})", teacherId);
            return ResponseEntity.ok().body(lessonService.findByTeacherId(teacherId));

    }


    @DeleteMapping(value = "byId/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        log.debug("requested: lesson  delete (id = {})", id);
        boolean deleted = lessonService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok().body("lesson with id = " + id + " was deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("lesson with id = " + id + " does not exist");
        }
    }

    @PostMapping(value = "lesson")
    public ResponseEntity<String> create(@RequestBody Lesson lesson) {
        boolean created = lessonService.create(lesson);
        if (created) {
            return ResponseEntity.ok().body("lesson with id = " + lesson.getId() + " was created");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("lesson with id = " + lesson.getId() + " already exists");
        }
    }

    @PutMapping(value = "lesson")
    public ResponseEntity<String> update(@RequestBody Lesson lesson) {
        Long id = lesson.getId();
        log.debug("requested: lesson  update (id = {})", id);
        boolean updated = lessonService.update(lesson);
        if (updated) {
            return ResponseEntity.ok().body("lesson with id = " + id + " was updated");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("lesson with id = " + id + " does not exist");
        }
    }


}





