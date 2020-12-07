package com.ncedu.knownetimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ncedu.knownetimpl.model.Tag;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

  boolean existsByTitle(String title);

  //иерархический запрос...
  @Query(value = "WITH RECURSIVE r AS (SELECT id, parent_id, title FROM tags WHERE parent_id is not null UNION SELECT tags.id, tags.parent_id, tags.title FROM tags JOIN r ON tags.parent_id = r.id")
  List<Tag> findWithParents(Long pare);

  //List<Tag> findByParentID(Long parentId);

  List<Tag> findByTitle(String title);
}
