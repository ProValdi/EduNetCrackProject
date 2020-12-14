package com.ncedu.knownetimpl.repository;

import com.ncedu.knownetimpl.model.entity.LearnRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearnRequestRepository extends JpaRepository<LearnRequest, Long> {
    
    List<LearnRequest> findByTeacherId(Long teacherId);
    
    List<LearnRequest> findByStudentId(Long studentId);
    
    List<LearnRequest> findByTeacherIdAndHiddenForTeacherAndStatusNot(Long teacherId, Boolean hidden, LearnRequest.Status status);
    
    List<LearnRequest> findByStudentIdAndHiddenForStudentAndStatusNot(Long teacherId, Boolean hidden, LearnRequest.Status status);
    
}
