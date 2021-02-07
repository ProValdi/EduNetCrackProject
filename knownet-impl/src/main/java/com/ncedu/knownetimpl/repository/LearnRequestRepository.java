package com.ncedu.knownetimpl.repository;

import com.ncedu.knownetimpl.model.entity.LearnRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearnRequestRepository extends JpaRepository<LearnRequest, Long> {
    List<LearnRequest> findByTeacherId(Long teacherId);
    List<LearnRequest> findByStudentId(Long studentId);
    List<LearnRequest> findByStudentIdAndHiddenForStudentFalse(Long studentId);
    List<LearnRequest> findByTeacherIdAndHiddenForTeacherFalse(Long teacherId);
}
