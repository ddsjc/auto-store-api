package com.example.ProjectUniver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class AgropidrSpecialist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String specSurname;
    private String specName;
    private String specPatronymic;
    private String specDepartment;
    private String specPosition;
    private String specPhoneNumber;
    private String addInfo;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "agropidrsSpecialist_connectionRequest",
            joinColumns = @JoinColumn(name = "agropidrSpecialist_id"),
            inverseJoinColumns = @JoinColumn(name = "connectionRequest_id"))
    private Set<ConnectionRequest> connectionRequests;

}
