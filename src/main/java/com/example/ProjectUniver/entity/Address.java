package com.example.ProjectUniver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
//@Table(name = "address")
@Data
@Entity
@NoArgsConstructor
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

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", addInfo='" + addInfo + '\'' +
                ", addressType=" + addressType +
                ", organization=" + organization +
                '}';
    }

    @ManyToOne
    //@OneToMany(mappedBy = "address")
    @JsonIgnore
    private Organization organization;

    public Address(String subjectName, String cityName, String streetName, String houseNumber, String addInfo) {
        this.subjectName = subjectName;
        this.cityName = cityName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.addInfo = addInfo;
    }
}
