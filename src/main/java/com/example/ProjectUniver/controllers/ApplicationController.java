package com.example.ProjectUniver.controllers;

import com.example.ProjectUniver.dto.ApplicationDto;
import com.example.ProjectUniver.dto.ServiceDopDto;
import com.example.ProjectUniver.entity.Application;
import com.example.ProjectUniver.entity.ServiceDop;
import com.example.ProjectUniver.repository.UserRepository;
import com.example.ProjectUniver.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {


    private final ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    public ApplicationController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("/createapplication")
    @Operation(summary = "Создать заявочку")

    public ResponseEntity<Application> createEventDraft(@RequestBody ApplicationDto applicationDTO,
                                                        @AuthenticationPrincipal UserDetails userDetails) {
        Application event = convertToEventDraft(applicationDTO);
        ServiceDop serviceDop= convertToServiceDop(applicationDTO.getServiceDopDto());
        event.setUser(userRepository.findUserByLogin(userDetails.getUsername()));
        applicationService.createApplication(event, serviceDop);
        return ResponseEntity.ok(event);
    }
    private Application convertToEventDraft(ApplicationDto applicationDTO) {
        return modelMapper.map(applicationDTO, Application.class);
    }
    private ServiceDop convertToServiceDop(ServiceDopDto serviceDopDto) {
        return modelMapper.map(serviceDopDto, ServiceDop.class);
    }
}
