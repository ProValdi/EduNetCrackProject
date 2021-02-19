package com.ncedu.knownetimpl.repository;

import com.ncedu.knownetimpl.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Long deleteByUsername(String username);

    boolean existsByUsername(String username);

    List<User> findByUsername(String username);

    List<User> findByGroup(String group);

    Optional<User> findByEmail(String email);

}
