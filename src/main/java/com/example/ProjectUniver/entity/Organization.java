package com.example.ProjectUniver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String organizationFullName;
    private String organizationShortName;
    private String inn;
    private String kpp;
    private String ogrn;
    private String responsiblePersonSurname;
    private String responsiblePersonName;
    private String responsiblePersonPatronymic;
    private String responsiblePersonPhoneNumber;
    private String responsiblePersonEmail;
    private String addInfo;

    @OneToMany(mappedBy = "organization")
    @JsonIgnore
    private Set<Address> addresses;

    @OneToMany (mappedBy = "organization")
    @JsonIgnore
    private Set<ConnectionRequest> connectionRequests;

    @OneToMany (mappedBy = "organization" )
    @JsonIgnore
    private List<Application> applications;
}
