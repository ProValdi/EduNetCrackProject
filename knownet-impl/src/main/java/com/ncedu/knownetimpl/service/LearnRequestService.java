package com.ncedu.knownetimpl.service;

import com.ncedu.knownetimpl.model.LearnRequestBody;
import com.ncedu.knownetimpl.model.entity.Lesson;
import com.ncedu.knownetimpl.model.entity.User;
import com.ncedu.knownetimpl.model.entity.LearnRequest;
import com.ncedu.knownetimpl.model.entity.LearnRequest.Status;
import com.ncedu.knownetimpl.repository.LearnRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LearnRequestService {
    private final LearnRequestRepository learnRequestRepository;
    private final UserService userService;
    private final LessonService lessonService;
    
    public LearnRequestService(LearnRequestRepository learnRequestRepository, UserService userService, LessonService lessonService) {
        this.learnRequestRepository = learnRequestRepository;
        this.userService = userService;
        this.lessonService = lessonService;
    }
    
    public List<LearnRequest> findAll() {
        return learnRequestRepository.findAll();
    }
    
    public Optional<LearnRequest> findById(Long id) {
        return learnRequestRepository.findById(id);
    }
    
    public boolean deleteById(Long id) {
        boolean exists = learnRequestRepository.existsById(id);
        if (exists) {
            learnRequestRepository.deleteById(id);
        }
        return exists;
    }
    
    public List<LearnRequest> findByTeacherId(Long teacherId) {
        return learnRequestRepository.findByTeacherId(teacherId);
    }
    
    public List<LearnRequest> findByStudentId(Long studentId) {
        return learnRequestRepository.findByStudentId(studentId);
    }
    
    public List<LearnRequest> findActiveByTeacherId(Long teacherId) {
        return learnRequestRepository.findByTeacherIdAndHiddenForTeacherFalse(teacherId);
    }
    
    public List<LearnRequest> findActiveByStudentId(Long studentId) {
        return learnRequestRepository.findByStudentIdAndHiddenForStudentFalse(studentId);
    }
    
    public boolean create(LearnRequest learnRequest) {
        boolean exists = learnRequest.getId() != null && learnRequestRepository.existsById(learnRequest.getId());
        if (!exists) {
            learnRequestRepository.save(learnRequest);
        }
        return !exists;
    }
    
    public boolean update(LearnRequest learnRequest) throws IllegalStateException {
        Optional<LearnRequest> oldLearnRequestOpt = findById(learnRequest.getId());
        if (oldLearnRequestOpt.isPresent()) {
            LearnRequest oldLearnRequest = oldLearnRequestOpt.get();
            
            Status oldStatus = oldLearnRequest.getStatus();
            Status newStatus = learnRequest.getStatus();
            
            if (ifCanChangeStatus(oldStatus, newStatus)) {
                log.debug(newStatus.getDescription());
                oldLearnRequest.setStatus(newStatus);
                
                oldLearnRequest.setHiddenForStudent(false);
                oldLearnRequest.setHiddenForTeacher(false);
                
                if (newStatus.isFinished()) {
                    oldLearnRequest.setIsFinished(true);
                }
            } else {
                oldLearnRequest.setHiddenForStudent(learnRequest.getHiddenForStudent());
                oldLearnRequest.setHiddenForTeacher(learnRequest.getHiddenForTeacher());
            }
            
            learnRequestRepository.save(oldLearnRequest);
        }
        
        return oldLearnRequestOpt.isPresent();
    }
    
    static private boolean ifCanChangeStatus(Status oldStatus, Status newStatus) throws IllegalStateException {
        if (oldStatus == newStatus) {
            return false;
        }
        
        switch (oldStatus) {
            case LESSON_REQUESTED:
                if (
                    newStatus == Status.LESSON_REQUEST_REJECTED ||
                    newStatus == Status.LESSON_REQUEST_ACCEPTED
                ) {
                    return true;
                } else {
                    throw new IllegalStateException("Unexpected value: " + newStatus);
                }
            case LESSON_REQUEST_ACCEPTED:
                if (
                    newStatus == Status.MEETING_CONFIRMED ||
                    newStatus == Status.MEETING_DISPROVED ||
                    newStatus == Status.MEETING_CANCELED
                ) {
                    return true;
                } else {
                    throw new IllegalStateException("Unexpected value: " + newStatus);
                }
            case LESSON_REQUEST_REJECTED:
            case MEETING_CONFIRMED:
            case MEETING_CANCELED:
            case MEETING_DISPROVED:
            default:
                throw new IllegalStateException("Unexpected value: " + newStatus);
        }
    }
    
    public LearnRequest makeFromBody(LearnRequestBody body) {
        LearnRequest learnRequest = new LearnRequest();
    
        learnRequest.setId(body.getId());
        learnRequest.setHiddenForStudent(body.getHiddenForStudent());
        learnRequest.setHiddenForTeacher(body.getHiddenForTeacher());
        learnRequest.setStatus(body.getStatus());
        learnRequest.setIsFinished(body.getStatus().isFinished());
    
        Optional<User> student;
        Optional<Lesson> lesson;
        
        if (body.getStudentId() == null) {
            student = Optional.empty();
        } else {
            student = userService.findById(body.getStudentId());
        }
        
        if (body.getLessonId() == null) {
            lesson = Optional.empty();
        } else {
            lesson = lessonService.findById(body.getLessonId());
        }
    
        if (lesson.isPresent()) {
            learnRequest.setTeacher(lesson.get().getTeacher());
        } else {
            learnRequest.setTeacher(new User());
        }
    
        learnRequest.setStudent(student.orElse(new User()));
        learnRequest.setLesson(lesson.orElse(new Lesson()));
        
        return learnRequest;
    }
}
