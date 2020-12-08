package com.ncedu.knownetimpl.service;

import com.ncedu.knownetimpl.model.entity.LearnRequest;
import com.ncedu.knownetimpl.repository.LearnRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnRequestService {
    private final LearnRequestRepository learnRequestRepository;
    
    public LearnRequestService(LearnRequestRepository learnRequestRepository) {
        this.learnRequestRepository = learnRequestRepository;
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
}
