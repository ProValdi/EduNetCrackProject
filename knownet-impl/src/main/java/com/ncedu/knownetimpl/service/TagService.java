package com.ncedu.knownetimpl.service;

import com.ncedu.knownetimpl.model.Tag;
import com.ncedu.knownetimpl.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
  private final TagRepository tagRepository;

  public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  public boolean deleteByTitle(String title) {
    boolean exists = tagRepository.existsByTitle(title);
    if (exists) {
      tagRepository.deleteByTitle(title);
    }
    return exists;
  }

  public List<Tag> findByTitle(String title) {
    return tagRepository.findByTitle(title);
  }

  public List<Tag> findBySemestr(int semestr) {
    return tagRepository.findBySemestr(semestr);
  }
}
