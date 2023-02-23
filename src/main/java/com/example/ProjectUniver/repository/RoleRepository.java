package com.example.ProjectUniver.repository;

import com.example.ProjectUniver.entity.ERole;
import com.example.ProjectUniver.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
