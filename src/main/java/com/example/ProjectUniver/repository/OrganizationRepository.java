package com.example.ProjectUniver.repository;

import com.example.ProjectUniver.entity.Organization;
import com.example.ProjectUniver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
