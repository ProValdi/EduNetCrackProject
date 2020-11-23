package com.ncedu.knownetweb;

import com.ncedu.knownetimpl.model.User;
import com.ncedu.knownetimpl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        log.debug("requested: users get    (all)");
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("byLogin/{login}")
    public ResponseEntity<User> findByLogin(@PathVariable(name = "login") String login) {
        log.debug("requested: user  get    (login = {})", login);
        return ResponseEntity.of(userService.findByLogin(login));
    }
    
    @GetMapping("byGroup/{group}")
    public ResponseEntity<List<User>> findByGroup(@PathVariable(name = "group") String group) {
        log.debug("requested: users get    (group = {})", group);
        return ResponseEntity.ok().body(userService.findByGroup(group));
    }
    
    @GetMapping(value = "byId/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        log.debug("requested: user  get    (id = {})", id);
        Optional<User> user = userService.findById(id);
        return ResponseEntity.of(user);
    }
    
    
    @DeleteMapping(value = "byLogin/{login}")
    public ResponseEntity<String> deleteByLogin(@PathVariable("login") String login) {
        log.debug("requested: user  delete (login = {})", login);
        boolean deleted = userService.deleteByLogin(login);
        if (deleted) {
            return ResponseEntity.ok().body("user with login = " + login + " was deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("user with login = " + login + " does not exist");
        }
    }
    
    @PostMapping(value = "user")
    public ResponseEntity<String> create(@RequestBody User user) {
        String login = user.getLogin();
        log.debug("requested: user  create (login = {})", login);
        boolean created = userService.create(user);
        if (created) {
            return ResponseEntity.ok().body("user with login = " + login + " was created");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("user with login = " + login + " already exists");
        }
    }
    
    @PutMapping(value = "user")
    public ResponseEntity<String> update(@RequestBody User user) {
        String login = user.getLogin();
        log.debug("requested: user  update (login = {})", login);
        boolean updated = userService.update(user);
        if (updated) {
            return ResponseEntity.ok().body("user with login = " + login + " was updated");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("user with login = " + login + " does not exist");
        }
    }
}