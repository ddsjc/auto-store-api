package com.example.ProjectUniver.controllers;

import com.example.ProjectUniver.dto.AddressDto;
import com.example.ProjectUniver.dto.AddressTypeDto;
import com.example.ProjectUniver.dto.ApplicationDto;
import com.example.ProjectUniver.dto.OrganizationDto;
import com.example.ProjectUniver.entity.*;
import com.example.ProjectUniver.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizationController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/createorganization")
    @Operation(summary = "Создать организацию")
    public ResponseEntity<Organization> createApplication(@RequestBody OrganizationDto organizationDto) {
        Organization organization = convertToOrganization(organizationDto);
        AddressType addressType = convertToAddressType(organizationDto.getAddressDto().getAddressTypeDto());
        Address address = convertToAddress(organizationDto.getAddressDto());
        organizationService.createOrganization(organization, address, addressType);
        return ResponseEntity.ok(organization);
    }

    private Organization convertToOrganization(OrganizationDto organizationDto) {
        return modelMapper.map(organizationDto, Organization.class);
    }

    private AddressType convertToAddressType(AddressTypeDto addressTypeDto) {
        return modelMapper.map(addressTypeDto, AddressType.class);
    }

    private Address convertToAddress(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }
     /* organization.getUser().getFirstName();
        Set<Address> addressSet = new HashSet(organization.getAddresses());
        for (Address address1:addressSet
             ) {
                address1.getAddressType();
                address1.getSubjectName();
        }*/
}
