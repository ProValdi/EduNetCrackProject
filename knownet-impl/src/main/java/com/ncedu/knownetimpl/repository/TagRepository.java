package com.ncedu.knownetimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ncedu.knownetimpl.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

  void deleteByTitle(String title);

  boolean existsByTitle(String title);

  Optional<Tag> findByTitle(String title);

}
