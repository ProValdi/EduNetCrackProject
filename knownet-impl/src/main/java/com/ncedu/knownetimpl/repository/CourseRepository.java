package com.ncedu.knownetimpl.repository;


import com.ncedu.knownetimpl.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    void deleteByName(String name);

    boolean existsByName(String name);

    List<Course> findByName(String name);

    List<Course> findByGroup(String group);

}

