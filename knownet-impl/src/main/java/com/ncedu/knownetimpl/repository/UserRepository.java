package com.ncedu.knownetimpl.repository;

import com.ncedu.knownetimpl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Long deleteByLogin(String login);
    
    boolean existsByLogin(String login);
    
    List<User> findByLogin(String login);
    
    List<User> findByGroup(String group);
    
//    List<User> findByFirstNameAndLastName(String firstName, String lastName);

}
