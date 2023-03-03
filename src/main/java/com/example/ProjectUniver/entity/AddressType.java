package com.example.ProjectUniver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
@Table (name = "addressType")
@Data
@Entity
public class AddressType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String addressTypeName;
    private String addInfo;

    @OneToMany (mappedBy = "addressType")
    @JsonIgnore
    private Set<Address> addresses;
}
