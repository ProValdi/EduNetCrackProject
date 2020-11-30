package com.ncedu.knownetimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ncedu.knownetimpl.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

  boolean existsByTitle(String title);

  //иерархический запрос...
  List<Tag> findWithParents(Long id);

  Optional<Tag> findByTitle(String title);

}
