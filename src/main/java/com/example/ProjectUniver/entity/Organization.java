package com.example.ProjectUniver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String organizationFullName;
    private String organizationPassword;
    private String organizationShortName;
    private String inn;
    private String kpp;
    private String ogrn;
    private Boolean approve;
    /*private String responsiblePersonSurname;
    private String responsiblePersonName;
    private String responsiblePersonPatronymic;
    private String responsiblePersonPhoneNumber;
    private String responsiblePersonEmail;
    private String addInfo;*/

    @OneToMany(mappedBy = "organization")
    @JsonIgnore
    private List<Address> addresses;

    @OneToMany (mappedBy = "organization")
    @JsonIgnore
    private Set<ConnectionRequest> connectionRequests;

    @OneToMany (mappedBy = "organization" )
    @JsonIgnore
    private List<Application> applications;

    @OneToOne
    @JsonIgnore
    private User user;

    public Organization( String organizationFullName, String organizationPassword, String organizationShortName, String inn, String kpp, String ogrn, List<Address> addresses, User user) {
        this.organizationFullName = organizationFullName;
        this.organizationPassword = organizationPassword;
        this.organizationShortName = organizationShortName;
        this.inn = inn;
        this.kpp = kpp;
        this.ogrn = ogrn;
        this.addresses = addresses;
        this.user = user;
    }

}
