package com.ncedu.knownetimpl.repository;

import com.ncedu.knownetimpl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    examples

//    @Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
//    User findUserByStatusAndNameNamedParams(
//            @Param("status") Integer status,
//            @Param("name") String name);
//
//    @Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
//    User findUserByStatusAndName(Integer status, String name);
//
//    @Query("SELECT u FROM User u WHERE u.status = 1")
//    Collection<User> findAllActiveUsers();
}
