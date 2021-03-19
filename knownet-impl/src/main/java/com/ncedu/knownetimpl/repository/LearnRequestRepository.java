package com.ncedu.knownetimpl.repository;

import com.ncedu.knownetimpl.model.entity.LearnRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearnRequestRepository extends JpaRepository<LearnRequest, Long> {
    List<LearnRequest> findByTeacherIdAndIsFinishedFalse(Long teacherId);
    List<LearnRequest> findByStudentIdAndIsFinishedFalse(Long studentId);
    List<LearnRequest> findByStudentIdAndHiddenForStudentFalseAndIsFinishedFalse(Long studentId);
    List<LearnRequest> findByTeacherIdAndHiddenForTeacherFalseAndIsFinishedFalse(Long teacherId);
}
