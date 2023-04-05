package com.example.ProjectUniver.dto;

import com.example.ProjectUniver.entity.User;
import lombok.Data;

@Data
public class GetApproveApplicationDto {
    private String firstName;
    private String username;
    private String phoneNumber;
    public GetApproveApplicationDto(String firstName, String username, String phoneNumber) {
        this.firstName = firstName;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }
}
