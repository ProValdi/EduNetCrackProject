package com.ncedu.knownetweb;

import com.ncedu.knownetimpl.model.LearnRequestBody;
import com.ncedu.knownetimpl.model.entity.LearnRequest;
import com.ncedu.knownetimpl.service.LearnRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/requests")
public class LearnRequestController {
    private final LearnRequestService learnRequestService;

    @Autowired
    public LearnRequestController(LearnRequestService learnRequestService) {
        this.learnRequestService = learnRequestService;
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<LearnRequest>> findAll() {
        log.debug("requested: learnRequests get    (all)");
        return ResponseEntity.ok().body(learnRequestService.findAll());
    }
    
    @GetMapping(value = "byId/{id}")
    public ResponseEntity<LearnRequest> findById(@PathVariable("id") Long id) {
        log.debug("requested: learnRequest  get    (id = {})", id);
        Optional<LearnRequest> request = learnRequestService.findById(id);
        return ResponseEntity.of(request);
    }
    
    @DeleteMapping(value = "byId/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        log.debug("requested: learnRequest  delete (id = {})", id);
        boolean deleted = learnRequestService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok().body("learnRequest with id = " + id + " was deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("learnRequest with id = " + id + " does not exist");
        }
    }
    
    @GetMapping("byTeacherId/{teacherId}")
    public ResponseEntity<List<LearnRequest>> findByTeacherId(
            @PathVariable(name = "teacherId") Long teacherId,
            @RequestParam(name = "activeOnly", defaultValue = "true", required = false) Boolean activeOnly
    ) {
        if (activeOnly) {
            log.debug("requested: learnRequests get    (teacherId = {}, active only)", teacherId);
            return ResponseEntity.ok().body(learnRequestService.findActiveByTeacherId(teacherId));
        } else {
            log.debug("requested: learnRequests get    (teacherId = {})", teacherId);
            return ResponseEntity.ok().body(learnRequestService.findByTeacherId(teacherId));
        }
    }
    
    @GetMapping("byStudentId/{studentId}")
    public ResponseEntity<List<LearnRequest>> findByStudentId(
            @PathVariable(name = "studentId") Long studentId,
            @RequestParam(name = "activeOnly", defaultValue = "true", required = false) Boolean activeOnly
    ) {
        if (activeOnly) {
            log.debug("requested: learnRequests get    (studentId = {}, active only)", studentId);
            return ResponseEntity.ok().body(learnRequestService.findActiveByStudentId(studentId));
        } else {
            log.debug("requested: learnRequests get    (studentId = {})", studentId);
            return ResponseEntity.ok().body(learnRequestService.findByStudentId(studentId));
        }
    }
    
    
    @PostMapping(value = "request")
    public ResponseEntity<String> create(@RequestBody LearnRequestBody learnRequestbody) {
        LearnRequest learnRequest = learnRequestService.makeFromBody(learnRequestbody);
        log.debug("requested: learnRequest  create (teacherId = {}, studentId = {}, lessonId = {})",
                learnRequest.getTeacher().getId(), learnRequest.getStudent().getId(),
                learnRequest.getLesson().getId());
        boolean created = learnRequestService.create(learnRequest);
        if (created) {
            return ResponseEntity.ok().body("learnRequest with id = " + learnRequest.getId() + " was created");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("learnRequest with id = " + learnRequest.getId() + " already exists");
        }
    }
    
    @PutMapping(value = "request")
    public ResponseEntity<String> update(@RequestBody LearnRequestBody learnRequestbody) {
        LearnRequest learnRequest = learnRequestService.makeFromBody(learnRequestbody);
        Long id = learnRequest.getId();
        log.debug("requested: learnRequest  update (id = {})", id);
        try {
            boolean updated = learnRequestService.update(learnRequest);
            if (updated) {
                return ResponseEntity.ok().body("learnRequest with id = " + id + " was updated");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("learnRequest with id = " + id + " does not exist");
            }
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("conflict in current and new status in learnRequest with id = " + id + ". " + e.getMessage());
        }
    }
    
}
