package com.example.ProjectUniver.dto;

import com.example.ProjectUniver.entity.ServiceDop;
import lombok.Data;

import java.util.List;

@Data
public class GetAllApplicationsDto {
    private Integer id;
    private String firstName;
    private String organizationName;
    private String price;
    private AddressDto address;

    public GetAllApplicationsDto(Integer id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }
}
