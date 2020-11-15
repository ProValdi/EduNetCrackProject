package com.ncedu.knownetimpl.service;

import com.ncedu.knownetimpl.model.User;
import com.ncedu.knownetimpl.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    public Optional<User> findByLogin(String login) {
        List<User> users = userRepository.findByLogin(login);
        if (users.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(users.get(0));
        }
    }
    
    public List<User> findByGroup(String group) {
        return userRepository.findByGroup(group);
    }
    
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    public boolean deleteById(Long id) {
        boolean exists = userRepository.existsById(id);
        if (exists) {
            userRepository.deleteById(id);
        }
        return exists;
    }
    
    public boolean create(User user) {
        boolean exists = userRepository.existsById(user.getId());
        if (!exists) {
            userRepository.save(user);
        }
        return !exists;
    }
    
    public boolean update(User user) {
        boolean exists = userRepository.existsById(user.getId());
        if (exists) {
            userRepository.save(user);
        }
        return exists;
    }
    
    
}
