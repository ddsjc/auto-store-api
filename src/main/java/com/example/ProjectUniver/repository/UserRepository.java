package com.example.ProjectUniver.repository;

import com.example.ProjectUniver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByLogin(String login);

    boolean existsByLogin(String username);

    boolean existsActorByEmail(String email);

}
