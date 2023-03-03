package com.example.ProjectUniver.repository;

import com.example.ProjectUniver.entity.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressTypeRepository extends JpaRepository<AddressType, Integer> {
}
