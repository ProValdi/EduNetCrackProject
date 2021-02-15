package com.ncedu.knownetimpl.service;

import com.ncedu.knownetimpl.model.entity.User;
import com.ncedu.knownetimpl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
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
        if(id != 0)
            return userRepository.findById(id);
        else
            return Optional.empty();
    }
    
    @Transactional
    public boolean deleteByLogin(String login) {
        return userRepository.deleteByLogin(login) != 0;
    }
    
    public boolean deleteById(Long id) {
        if(id == 0)
            return false;
        boolean exists = userRepository.existsById(id);
        if (exists) {
            userRepository.deleteById(id);
        }
        return exists;
    }
    
    public boolean create(User user) {
        boolean exists = userRepository.existsByLogin(user.getLogin());
        if (!exists) {
            userRepository.save(user);
        }
        return !exists;
    }
    
    public boolean update(User user) {
        Optional<User> oldUserOpt = findByLogin(user.getLogin());
        if (oldUserOpt.isPresent()) {
            User oldUser = oldUserOpt.get();
            
            oldUser.setFirstName(user.getFirstName());
            oldUser.setLastName(user.getLastName());
            oldUser.setGroup(user.getGroup());
            oldUser.setVkLink(user.getVkLink());
            oldUser.setTelegramLink(user.getTelegramLink());
            oldUser.setEmail(user.getEmail());
            oldUser.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(oldUser);
        }
        return oldUserOpt.isPresent();
    }
    
    
}
