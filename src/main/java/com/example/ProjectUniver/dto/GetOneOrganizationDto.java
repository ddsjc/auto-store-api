package com.example.ProjectUniver.dto;

import lombok.Data;

@Data
public class GetOneOrganizationDto {
    Integer id;
    String shortName;

    public GetOneOrganizationDto(Integer id, String shortName) {
        this.id = id;
        this.shortName = shortName;
    }
}
