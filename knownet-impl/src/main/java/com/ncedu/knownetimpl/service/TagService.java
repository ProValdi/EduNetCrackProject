package com.ncedu.knownetimpl.service;

import com.ncedu.knownetimpl.model.entity.Tag;
import com.ncedu.knownetimpl.repository.TagRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
  private final TagRepository tagRepository;

  public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  public Optional<Tag> findById(Long id) {
    return tagRepository.findById(id);
  }

  public List<Tag> findWithParents(Long id) {
    return tagRepository.findWithParents(id);
  }

  public List<Tag> getChildren(Long id) {
    return tagRepository.findByParentId(id);
  }

  @Transactional
  public boolean deleteById(Long id) {
    boolean exists = tagRepository.existsById(id);
    if (exists) {
      tagRepository.deleteById(id);
    }
    return exists;
  }

  public List<Tag> findAll() {
    return tagRepository.findAll();
  }

  public List<Tag> findByTitle(String title) {
    return tagRepository.findByTitle(title);
  }

  public boolean create(Tag tag) {
    boolean exists = tag.getId() != null && tagRepository.existsById(tag.getId());
    if (!exists) {
      tagRepository.save(tag);
    }

    return (!exists);
  }

  public boolean update(Tag tag) {
    Optional<Tag> oldTagOpt = findById(tag.getId());
    if (oldTagOpt.isPresent()) {
      Tag oldTag = oldTagOpt.get();

      oldTag.setTitle(tag.getTitle());
      oldTag.setParentId(tag.getParentId());
      tagRepository.save(oldTag);
    }

    return oldTagOpt.isPresent();
  }
}
