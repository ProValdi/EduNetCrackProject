package com.ncedu.knownetimpl.repository;

import com.ncedu.knownetimpl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ncedu.knownetimpl.model.Tag;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

  void deleteByTitle(String title);

  boolean existsByTitle(String title);

  List<Tag> findByTitle(String title);

  List<Tag> findBySemestr(int semestr);

}
