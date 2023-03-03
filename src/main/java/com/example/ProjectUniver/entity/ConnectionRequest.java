package com.example.ProjectUniver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class ConnectionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String regNumber;
    private String dateBegin;
    private String dateEnd;
    private String status;
    private String addInfo;

    @ManyToOne
    @JsonIgnore
    private Organization organization;

    //@ManyToMany
    //@JsonIgnore
    //private Set<AgropidrSpecialist> agropidorsSpecialists;

}
