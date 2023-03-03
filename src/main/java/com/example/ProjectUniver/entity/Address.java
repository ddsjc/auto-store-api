package com.example.ProjectUniver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
//@Table(name = "address")
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String subjectName;
    private String cityName;
    private String streetName;
    private String houseNumber;
    private String addInfo;

    @ManyToOne
    @JsonIgnore
    private AddressType addressType;

    @ManyToOne
    //@OneToMany(mappedBy = "address")
    @JsonIgnore
    private Organization organization;
}
