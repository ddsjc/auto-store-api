package com.example.ProjectUniver.repository;

import com.example.ProjectUniver.entity.Application;
import com.example.ProjectUniver.entity.ServiceDop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDopRepository extends JpaRepository<ServiceDop, Integer> {

}
