package com.example.ProjectUniver.controllers;

import com.example.ProjectUniver.dto.*;
import com.example.ProjectUniver.entity.*;
import com.example.ProjectUniver.repository.ApplicationRepository;
import com.example.ProjectUniver.repository.UserRepository;
import com.example.ProjectUniver.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ApplicationController {


    private final ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    public ApplicationController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("/createapplication")
    @Operation(summary = "Создать заявочку")

    public ResponseEntity<MessageResponse> createApplication(@RequestBody ApplicationDto applicationDTO,
                                                             @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findUserByLogin(userDetails.getUsername());
        if(user.getOrganization().getApprove() == false){
            return new ResponseEntity<>(new MessageResponse("Профиль организации не подтвержден"), HttpStatus.BAD_REQUEST);
        }
        Application event = convertToApplication(applicationDTO);
        //Address address = convertToAddress(applicationDTO.getAddressDto());
        //AddressType addressType = convertToAddressType(applicationDTO.getAddressDto().getAddressTypeDto());
        ServiceDop serviceDop= convertToServiceDop(applicationDTO.getServiceDopDto());
        event.setUser(user);
        applicationService.createApplication(event, serviceDop,user);
        return new ResponseEntity<>(new MessageResponse("Заявка успешно создана"), HttpStatus.BAD_REQUEST);

    }
//    @PostMapping("/submitapplication")
//    @Operation(summary = "Выбрать")
//    public ResponseEntity<MessageResponse> submitApplication (@RequestBody GetApplicationDto getApplicationDto,@AuthenticationPrincipal UserDetails userDetails){
//       Optional<Application> application = applicationRepository.findById(getApplicationDto.getId());
//       application.getClass().getFields().ge
//    }

    private Application convertToApplication(ApplicationDto applicationDTO) {
        return modelMapper.map(applicationDTO, Application.class);
    }
//    private Application convertToApplication(GetApplicationDto applicationDTO) {
//        return modelMapper.map(applicationDTO, Application.class);
//    }
    private ServiceDop convertToServiceDop(ServiceDopDto serviceDopDto) {
        return modelMapper.map(serviceDopDto, ServiceDop.class);
    }
}
