package com.ncedu.knownetimpl.service;

import com.ncedu.knownetimpl.model.LearnRequestBody;
import com.ncedu.knownetimpl.model.entity.Lesson;
import com.ncedu.knownetimpl.model.entity.User;
import com.ncedu.knownetimpl.model.entity.LearnRequest;
import com.ncedu.knownetimpl.repository.LearnRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LearnRequestService {
    private final LearnRequestRepository learnRequestRepository;
    private final UserService userService;
    private final LessonService lessonService;

    @Autowired
    public LearnRequestService(LearnRequestRepository learnRequestRepository, UserService userService, LessonService lessonService) {
        this.learnRequestRepository = learnRequestRepository;
        this.userService = userService;
        this.lessonService = lessonService;
    }
    
    public List<LearnRequest> findAll() {
        return learnRequestRepository.findAll();
    }
    
    public Optional<LearnRequest> findById(Long id) {
        if(id != null)
            return learnRequestRepository.findById(id);
        else {
            log.warn("requested LearnRequest with null id");
            return Optional.empty();
        }
    }
    
    public boolean deleteById(Long id) {
        boolean exists = false;
        if(id != null)
            exists = learnRequestRepository.existsById(id);
        if (exists) {
            log.warn("requested LearnRequest with null id");
            learnRequestRepository.deleteById(id);
        }
        return exists;
    }
    
    public List<LearnRequest> findByTeacherId(Long teacherId) {
        if(teacherId != null)
            return learnRequestRepository.findByTeacherId(teacherId);
        else
        {
            log.warn("requested teacher with null id");
            return new ArrayList<LearnRequest>(0);
        }
    }
    
    public List<LearnRequest> findByStudentId(Long studentId) {
        if(studentId != null)
            return learnRequestRepository.findByStudentId(studentId);
        else
        {
            log.warn("requested user with null id");
            return new ArrayList<LearnRequest>(0);
        }
    }
    
    public List<LearnRequest> findActiveByTeacherId(Long teacherId) {
        if(teacherId != null)
            return learnRequestRepository.findByTeacherIdAndHiddenForTeacherAndStatusNot(
                    teacherId, false, LearnRequest.Status.CONNECTION_FINISHED);
        else
        {
            log.warn("requested teacher with null id");
            return new ArrayList<LearnRequest>(0);
        }
    }
    
    public List<LearnRequest> findActiveByStudentId(Long studentId) {
        if(studentId != null)
            return learnRequestRepository.findByStudentIdAndHiddenForStudentAndStatusNot(
                   studentId, false, LearnRequest.Status.CONNECTION_FINISHED);
        else
        {
            log.warn("requested student with null id");
            return new ArrayList<LearnRequest>(0);
        }
    }
    
    public boolean create(LearnRequest learnRequest) {
        boolean exists = learnRequest.getId() != null && learnRequestRepository.existsById(learnRequest.getId());
        if (!exists) {
            learnRequestRepository.save(learnRequest);
        }
        return !exists;
    }
    
    public boolean update(LearnRequest learnRequest) {
        if(learnRequest.getId() == null)
        {
            log.warn("requested learnRequest with null id");
            return false;
        }
        Optional<LearnRequest> oldLearnRequestOpt = findById(learnRequest.getId());
        if (oldLearnRequestOpt.isPresent()) {
            LearnRequest oldLearnRequest = oldLearnRequestOpt.get();
            
            oldLearnRequest.setHiddenForStudent(learnRequest.getHiddenForStudent());
            oldLearnRequest.setHiddenForTeacher(learnRequest.getHiddenForTeacher());
            
            LearnRequest.Status newStatus = learnRequest.getStatus();
            if (oldLearnRequest.getStatus() != newStatus) {
                log.debug(newStatus.getDescription());
                oldLearnRequest.setStatus(newStatus);
            }
            
            learnRequestRepository.save(oldLearnRequest);
        }
        
        return oldLearnRequestOpt.isPresent();
    }
    
    public LearnRequest makeFromBody(LearnRequestBody body) {
        LearnRequest learnRequest = new LearnRequest();

        learnRequest.setId(body.getId());
        learnRequest.setHiddenForStudent(body.getHiddenForStudent());
        learnRequest.setHiddenForTeacher(body.getHiddenForTeacher());
        learnRequest.setStatus(body.getStatus());
    
        Optional<User> student;
        Optional<User> teacher;
        Optional<Lesson> lesson;
        
        if (body.getStudentId() == null) {
            log.warn("Student in body with null id");
            student = Optional.empty();
        } else {
            student = userService.findById(body.getStudentId());
        }
        
        if (body.getTeacherId() == null) {
            log.warn("Teacher in body with null id");
            teacher = Optional.empty();
        } else {
            teacher = userService.findById(body.getTeacherId());
        }
        
        if (body.getLessonId() == null) {
            log.warn("Lesson in body with null id");
            lesson = Optional.empty();
        } else {
            lesson = lessonService.findById(body.getLessonId());
        }
    
        learnRequest.setStudent(student.orElse(new User()));
        learnRequest.setTeacher(teacher.orElse(new User()));
        learnRequest.setLesson(lesson.orElse(new Lesson()));
        
        return learnRequest;
    }
}
