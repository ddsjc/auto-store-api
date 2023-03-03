package com.example.ProjectUniver.dto;

import lombok.Data;

@Data
public class OrganizationDto {
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
    private AddressDto addressDto;
}
