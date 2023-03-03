package com.example.ProjectUniver.dto;


import lombok.Data;

@Data
public class AddressDto {

    private String subjectName;
    private String cityName;
    private String streetName;
    private String houseNumber;
    private String addInfo;
    private AddressTypeDto addressTypeDto;
}
