package com.example.ProjectUniver.dto;

import com.example.ProjectUniver.entity.Address;
import com.example.ProjectUniver.entity.ServiceDop;
import com.example.ProjectUniver.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class GetApplicationDto {
    private Integer id;
    private String firstName;
    private List<Address> addresses;
    private String organizationName;
    private List<ServiceDop> serviceDopList;


    public GetApplicationDto(Integer id, String firstName) {
        this.id = id;
        this.firstName = firstName;
        //this.serviceDopDtoList = serviceDopDtoList;
    }
}

