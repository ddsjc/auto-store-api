package com.example.ProjectUniver.dto;

import com.example.ProjectUniver.entity.ServiceDop;
import lombok.Data;

import java.util.List;

@Data
public class GetApplicationDto {
    private Integer id;
    private String firstName;
    private String dateApplication;
    private String addInfo;
    private List<ServiceDop> serviceDopList;

    public GetApplicationDto(Integer id, String dateApplication, String addInfo, String firstName) {
        this.id = id;
        this.dateApplication = dateApplication;
        this.addInfo = addInfo;
        this.firstName = firstName;
        //this.serviceDopDtoList = serviceDopDtoList;
    }
}

