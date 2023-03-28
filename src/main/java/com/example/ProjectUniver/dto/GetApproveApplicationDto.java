package com.example.ProjectUniver.dto;

import com.example.ProjectUniver.entity.User;
import lombok.Data;

@Data
public class GetApproveApplicationDto {
    private String firstName;



    private String username;
    public GetApproveApplicationDto(String firstName, String username) {
        this.firstName = firstName;
        this.username = username;
    }
}
