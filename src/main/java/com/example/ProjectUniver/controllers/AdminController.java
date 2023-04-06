package com.example.ProjectUniver.controllers;

import com.example.ProjectUniver.dto.*;
import com.example.ProjectUniver.entity.Address;
import com.example.ProjectUniver.entity.Application;
import com.example.ProjectUniver.entity.Organization;
import com.example.ProjectUniver.entity.ServiceDop;
import com.example.ProjectUniver.repository.OrganizationRepository;
import com.example.ProjectUniver.repository.UserRepository;
import com.example.ProjectUniver.service.OrganizationService;
import com.example.ProjectUniver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
//    @Autowired
//    OrganizationRepository organizationRepository;
//    @GetMapping("/checkorganization")
//    private ResponseEntity<List<GetOrganizationDto>>GetOrganization(){
//        List<Organization> organizationList = organizationRepository.findAll();
//        List<GetOrganizationDto> getOrganizationDtos = new ArrayList<>();
//        for (Organization organization:organizationList
//             ) { if(organization.getApprove()==false){
//                GetOrganizationDto getOrganizationDto = new GetOrganizationDto(organization.getUser().getFirstName()
//                        ,organization.getUser().getLastName(),
//                        organization.getUser().getPatronymic(),
//                        organization.getUser().getPhoneNumber(),
//                        organization.getUser().getLogin(),organization.getUser().getEmail(),
//                        organization.getOrganizationFullName(),
//                        organization.getOrganizationShortName(),
//                        organization.getInn(),organization.getKpp(), organization.getOgrn());
//            Set<Address> addressSet = new HashSet(organization.getAddresses());
//            Set<Address> addressSet1 = new HashSet<>();
//            for (Address address1:addressSet
//            ) {
//                addressSet1.add(
//                        new Address(address1.getSubjectName(),address1.getCityName(),address1.getStreetName(),address1.getHouseNumber(),address1.getAddInfo()));
//            }
//                getOrganizationDto.setAddress(addressSet1);
//                getOrganizationDtos.add(getOrganizationDto);
//             }
//        }
//
//        return ResponseEntity.ok(getOrganizationDtos);
//    }
@Autowired
OrganizationService organizationService;
@Autowired
    OrganizationRepository organizationRepository;
    @GetMapping("/checkapproveorganization")
    private ResponseEntity <List<GetApproveOrganizationDto> >GetApproveOrganization(){
        List<Organization> organizationList = organizationRepository.findAll();
        List<GetApproveOrganizationDto> getApproveOrganizationDtos = new ArrayList<>();
        for (Organization organization:organizationList
        ) { if(organization.getApprove()==true){
            GetApproveOrganizationDto getApproveOrganizationDto = new GetApproveOrganizationDto(organization.getUser().getFirstName()
                    ,organization.getUser().getLastName(),
                    organization.getUser().getPatronymic(),
                    organization.getUser().getPhoneNumber(),
                    organization.getUser().getLogin(),organization.getUser().getEmail(),
                    organization.getOrganizationFullName(),
                    organization.getOrganizationShortName(),
                    organization.getInn(),organization.getKpp(), organization.getOgrn(),organization.getApprove());


            getApproveOrganizationDto.setAddress(organization.getAddresses());
            getApproveOrganizationDtos.add(getApproveOrganizationDto);
        }
        }
        return ResponseEntity.ok(getApproveOrganizationDtos);

    }
    @GetMapping("/onenonapproveorganization")
    private ResponseEntity <List<GetOrganizationDto>> GetOrganization(@RequestParam Integer id){
        Organization organization = organizationRepository.findById(id).get();
        List<GetOrganizationDto> getOrganizationDtos = new ArrayList<>();
          if(organization.getApprove()==false){
            GetOrganizationDto getOrganizationDto = new GetOrganizationDto(organization.getId(), organization.getUser().getFirstName()
                    ,organization.getUser().getLastName(),
                    organization.getUser().getPatronymic(),
                    organization.getUser().getPhoneNumber(),
                    organization.getUser().getLogin(),organization.getUser().getEmail(),
                    organization.getOrganizationFullName(),
                    organization.getOrganizationShortName(),
                    organization.getInn(),organization.getKpp(), organization.getOgrn(),organization.getApprove());


            getOrganizationDto.setAddress(organization.getAddresses());
            getOrganizationDtos.add(getOrganizationDto);
        }
        return ResponseEntity.ok(getOrganizationDtos);
    }

/*    @GetMapping("/checkorganization")
    private ResponseEntity <List<GetOrganizationDto> >GetOrganization(){
        List<Organization> organizationList = organizationRepository.findAll();
        List<GetOrganizationDto> getOrganizationDtos = new ArrayList<>();
        for (Organization organization:organizationList
        ) { if(organization.getApprove()==false){
            GetOrganizationDto getOrganizationDto = new GetOrganizationDto(organization.getId(), organization.getUser().getFirstName()
                    ,organization.getUser().getLastName(),
                    organization.getUser().getPatronymic(),
                    organization.getUser().getPhoneNumber(),
                    organization.getUser().getLogin(),organization.getUser().getEmail(),
                    organization.getOrganizationFullName(),
                    organization.getOrganizationShortName(),
                    organization.getInn(),organization.getKpp(), organization.getOgrn(),organization.getApprove());


            getOrganizationDto.setAddress(organization.getAddresses());
            getOrganizationDtos.add(getOrganizationDto);
        }
        }
        return ResponseEntity.ok(getOrganizationDtos);
    }*/
//for (Organization organization:organizationList)
       @GetMapping("/checkorganization")
        private ResponseEntity<List<GetOneOrganizationDto>>GetOneOrganization(){
        List<Organization> organizationList = organizationRepository.findAll();
        List<GetOneOrganizationDto> getOneOrganizationDtos = new ArrayList<>();
        for (Organization organization:organizationList)
        if (organization.getApprove()==false) {
            GetOneOrganizationDto getOneOrganizationDto = new GetOneOrganizationDto(organization.getId(), organization.getOrganizationShortName());
            getOneOrganizationDtos.add(getOneOrganizationDto);
        }
        return ResponseEntity.ok(getOneOrganizationDtos);
    }
     /*      @GetMapping("/onenonapproveorganization")
    private ResponseEntity<List<GetOneOrganizationDto>>GetOneOrganization(@RequestParam Integer id){
        Organization organization = organizationRepository.findById(id).get();
        List<GetOneOrganizationDto> getOneOrganizationDtos = new ArrayList<>();
        if (organization.getApprove()==false) {
            GetOneOrganizationDto getOneOrganizationDto = new GetOneOrganizationDto(organization.getId(), organization.getOrganizationShortName());
            getOneOrganizationDtos.add(getOneOrganizationDto);
        }
        return ResponseEntity.ok(getOneOrganizationDtos);
    }*/


    @PatchMapping ("/approve")
    private ResponseEntity<String> ApproveChange(@RequestParam String login){
        Organization organization = organizationService.findOrganizationByLogin(login);
        organization.setApprove(true);
        organizationRepository.save(organization);
        return ResponseEntity.ok("Организация подтверждена");
    }
}
