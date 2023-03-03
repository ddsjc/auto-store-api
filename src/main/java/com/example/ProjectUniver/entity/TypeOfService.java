package com.example.ProjectUniver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

//@Table (name = "type_of_service")
@Data
@Entity
public class TypeOfService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String typeCode;
    private String typeName;

    @OneToMany (mappedBy = "typeOfService")
    @JsonIgnore
    private Set<ServiceDop> serviceDopSet;
}
