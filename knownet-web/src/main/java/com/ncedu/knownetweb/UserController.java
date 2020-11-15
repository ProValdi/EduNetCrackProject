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
//        if (user.isPresent()) {
//            return ResponseEntity.ok().body(user.get());
//        } else {
//            return ResponseEntity.noContent().build(); // 204
//        }
        return ResponseEntity.of(user); // 404 if empty
        
    }
    
    
    @DeleteMapping(value = "byId/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        log.debug("requested: user  delete (id = {})", id);
        boolean deleted = userService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok().body("user with id = " + id + " was deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user with id = " + id + " does not exist");
        }
    }
    
    @PostMapping(value = "user")
    public ResponseEntity<String> create(@RequestBody User user) {
        Long id = user.getId();
        log.debug("requested: user  create (id = {})", id);
        boolean created = userService.create(user);
        if (created) {
            return ResponseEntity.ok().body("user with id = " + id + " was created");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user with id = " + id + " already exists");
        }
    }
    
    @PutMapping(value = "user")
    public ResponseEntity<String> update(@RequestBody User user) {
        Long id = user.getId();
        log.debug("requested: user  update (id = {})", id);
        boolean updated = userService.update(user);
        if (updated) {
            return ResponseEntity.ok().body("user with id = " + id + " was updated");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user with id = " + id + " does not exist");
        }
    }
}
