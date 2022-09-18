package org.financemanager.repository;

import org.financemanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select u from User u order by u.id asc")
    List<User> findAll();

    Optional<User> findByUsername(String username);
}
