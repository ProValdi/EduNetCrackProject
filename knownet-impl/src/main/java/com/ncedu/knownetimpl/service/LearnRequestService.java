package com.ncedu.knownetimpl.service;

import com.ncedu.knownetimpl.model.LearnRequestBody;
import com.ncedu.knownetimpl.model.User;
import com.ncedu.knownetimpl.model.entity.LearnRequest;
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
    
    public LearnRequestService(LearnRequestRepository learnRequestRepository, UserService userService) {
        this.learnRequestRepository = learnRequestRepository;
        this.userService = userService;
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
        return learnRequestRepository.findByTeacherIdAndHiddenForTeacherAndStatusNot(
                teacherId, false, LearnRequest.Status.CONNECTION_FINISHED);
    }
    
    public List<LearnRequest> findActiveByStudentId(Long studentId) {
        return learnRequestRepository.findByStudentIdAndHiddenForStudentAndStatusNot(
                studentId, false, LearnRequest.Status.CONNECTION_FINISHED);
    }
    
    public boolean create(LearnRequest learnRequest) {
        boolean exists = learnRequest.getId() != null && learnRequestRepository.existsById(learnRequest.getId());
        if (!exists) {
            learnRequestRepository.save(learnRequest);
        }
        return !exists;
    }
    
    public boolean update(LearnRequest learnRequest) {
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
    
        Optional<User> student = userService.findById(body.getStudentId());
        Optional<User> teacher = userService.findById(body.getTeacherId());
//        todo activate
//        Optional<Lesson> lesson = userService.findById(body.getLessonId());
    
        learnRequest.setStudent(student.orElse(new User()));
        learnRequest.setTeacher(teacher.orElse(new User()));
//        learnRequest.setLesson(lesson.orElse(new Lesson()));
        
        return learnRequest;
    }
}
