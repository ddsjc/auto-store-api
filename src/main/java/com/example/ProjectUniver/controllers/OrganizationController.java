package com.example.ProjectUniver.controllers;

import com.example.ProjectUniver.dto.*;
import com.example.ProjectUniver.entity.*;
import com.example.ProjectUniver.repository.ApplicationRepository;
import com.example.ProjectUniver.repository.UserRepository;
import com.example.ProjectUniver.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("organization")
public class OrganizationController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/usersubmit")
    private ResponseEntity <List<GetApproveApplicationDto>>GetApplication(){
        List<Application> applicationList = applicationRepository.findAll();
        List<GetApproveApplicationDto> getApplicationDtos = new ArrayList<>();
        for (Application application:applicationList
        ) { if(!application.getUser().isEmpty()){
            for (User user:application.getUser()){
            GetApproveApplicationDto getApplicationDto = new  GetApproveApplicationDto(application.getFirstName(),user.getLogin(), user.getPhoneNumber());

            getApplicationDtos.add(getApplicationDto);}}
        }
        return ResponseEntity.ok(getApplicationDtos);
    }
    @PostMapping("/approveapplication")
    public ResponseEntity<MessageResponse> approveApplication (@RequestBody ApproveApplicationDto approveApplicationDto ){
        User user = userRepository.findUserByLogin(approveApplicationDto.getUsername());
        Application application = applicationRepository.findById(approveApplicationDto.getId()).get();
        user.getApproveApplication().add(application);
        application.getUser().add(user);
        applicationRepository.save(application);
        userRepository.save(user);
        return new ResponseEntity<>(new MessageResponse("Заявка одобрена"), HttpStatus.OK);

    }
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
