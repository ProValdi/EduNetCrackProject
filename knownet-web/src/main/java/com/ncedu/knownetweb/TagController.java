package com.ncedu.knownetweb;

import com.ncedu.knownetimpl.model.Tag;
import com.ncedu.knownetimpl.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/tags")
public class TagController {
  private final TagService tagService;

  @Autowired
  private TagController(TagService tagService) {
    this.tagService = tagService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Tag>> findAll() {
    log.debug("requested: tag get     (all)");
    return ResponseEntity.ok(tagService.findAll());
  }

  @GetMapping("byTitle/{title}")
  public ResponseEntity<Optional<Tag>> findByTitle(@PathVariable(name = "title") String title) {
    log.debug("requested: tag get    (title = {})", title);
    return ResponseEntity.ok().body(tagService.findByTitle(title));
  }

  @GetMapping("byId/{id}")
  public ResponseEntity<Tag> findByID(@PathVariable("id") Long id) {
    log.debug("requested: tag get      (id = {})", id);
    Optional<Tag> tag = tagService.findById(id);
    return ResponseEntity.of(tag);
  }


  @DeleteMapping(value = "byId/{id}")
  public ResponseEntity<String> deleteByTitle(@PathVariable("id") Long id) {
    log.debug("requested: tag  delete (id = {})", id);
    boolean deleted = tagService.deleteById(id);
    if (deleted) {
      return ResponseEntity.ok().body("tag with id = " + id + " was deleted");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("tag with id = " + id + " does not exist");
    }
  }

  @PostMapping(value = "tag")
  public ResponseEntity<String> create(@RequestBody Tag tag) {
    String title = tag.getTitle();
    log.debug("requested: tag  create (title = {})", title);
    boolean created = tagService.create(tag);
    if (created) {
      return ResponseEntity.ok().body("tag with title = " + title + " was created");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("tag with title = " + title + " already exists");
    }
  }

  @PostMapping(value = "tag")
  public ResponseEntity<String> update(@RequestBody Tag tag) {
    String title = tag.getTitle();
    log.debug("requested: tag  update (title = {})", title);
    boolean updated = tagService.update(tag);
    if (updated) {
      return ResponseEntity.ok().body("tag with title = " + title + " was updated");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("tag with title = " + title + " does not exist");
    }
  }
}
