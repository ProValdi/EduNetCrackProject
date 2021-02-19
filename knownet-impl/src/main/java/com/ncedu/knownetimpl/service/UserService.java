package com.ncedu.knownetimpl.service;

import com.ncedu.knownetimpl.model.entity.User;
import com.ncedu.knownetimpl.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public Optional<User> findByUsername(String login) {
    List<User> users = userRepository.findByUsername(login);
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

  @Transactional
  public boolean deleteByUsername(String login) {
    return userRepository.deleteByUsername(login) != 0;
  }

  public boolean deleteById(Long id) {
    boolean exists = userRepository.existsById(id);
    if (exists) {
      userRepository.deleteById(id);
    }
    return exists;
  }

  public boolean create(User user) {
    boolean exists = userRepository.existsByUsername(user.getUsername());
    if (!exists) {
      userRepository.save(user);
    }
    return !exists;
  }

  public boolean update(User user) {
    Optional<User> oldUserOpt = findByUsername(user.getUsername());
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

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("user with this email not found")));
  }

  public String signUpUser(User user) {
    boolean userExist = userRepository.findByEmail(user.getEmail()).isPresent();

    if(userExist) {
      throw new IllegalStateException("user with this email are already exists");
    }

    String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

    user.setPassword(encodedPassword);

    userRepository.save(user);

    // TODO: SEND CONFIRMATION TOKEN

    return "it works: sign up";
  }


}
