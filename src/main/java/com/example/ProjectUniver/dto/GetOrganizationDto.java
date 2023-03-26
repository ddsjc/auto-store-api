package com.example.ProjectUniver.dto;

import com.example.ProjectUniver.entity.Address;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class GetOrganizationDto {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;
    private String username;
    private String email;
    private String organizationFullName;
    private String organizationShortName;
    private String inn;
    private String kpp;
    private String ogrn;
    private List<Address> address;
    private Boolean approve;

    public GetOrganizationDto(String firstName, String lastName, String patronymic, String phoneNumber, String username, String email, String organizationFullName, String organizationShortName, String inn, String kpp, String ogrn, Boolean approve) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.email = email;
        this.organizationFullName = organizationFullName;
        this.organizationShortName = organizationShortName;
        this.inn = inn;
        this.kpp = kpp;
        this.ogrn = ogrn;
        this.approve = approve;
        //this.address = addressSet;
    }

}
