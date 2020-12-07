package com.ncedu.knownetimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ncedu.knownetimpl.model.Tag;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

  boolean existsByTitle(String title);

  List<Tag> findWithParents(Long pare);

  List<Tag> findByTitle(String title);
}
