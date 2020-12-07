package com.ncedu.knownetimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ncedu.knownetimpl.model.Tag;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

  boolean existsByTitle(String title);

  @Query(value = "SELECT id, title, parent_id FROM tags START WITH id = :id CONNECT BY PRIOR id = parent_id", nativeQuery = true)
  List<Tag> findWithParents(Long id);

  List<Tag> findByTitle(String title);
}
