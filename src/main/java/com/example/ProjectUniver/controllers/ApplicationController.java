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
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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
        event.setOrganization(user.getOrganization());
        applicationService.createApplication(event, serviceDop);
        return new ResponseEntity<>(new MessageResponse("Заявка успешно создана"), HttpStatus.OK);

    }
    @PostMapping("/submitapplication")
    @Operation(summary = "Выбрать")
    public ResponseEntity<MessageResponse> submitApplication (@RequestParam Integer id,@AuthenticationPrincipal UserDetails userDetails){
       Application application = applicationRepository.findById(id).get();
        User user = userRepository.findUserByLogin(userDetails.getUsername());
          user.setApplication(application);
        userRepository.save(user);
       return new ResponseEntity<>(new MessageResponse("Заявка выбрана"), HttpStatus.OK);

    }
    @GetMapping("/allapplications")
    private ResponseEntity <List<GetAllApplicationsDto>>GetAllApplication(){
        List<Application> applicationList = applicationRepository.findAll();
        List<GetAllApplicationsDto> getAllApplicationDtos = new ArrayList<>();
        for (Application application:applicationList
        ) {
            GetAllApplicationsDto getAllApplicationsDto = new GetAllApplicationsDto(application.getId(), application.getFirstName());
            getAllApplicationsDto.setOrganizationName(application.getOrganization().getOrganizationShortName());
            getAllApplicationsDto.setAddress(convertToAddressDto(application.getOrganization().getAddresses().get(0)));
            List<Integer> convertToInteger = new ArrayList<>();
            for(ServiceDop serviceDop : application.getServiceDop()){
                convertToInteger.add(Integer.parseInt(serviceDop.getPrice()));
            }
            getAllApplicationsDto.setPrice(String.valueOf(convertToInteger.stream().min(Comparator.naturalOrder()).get()));
            //application.getServiceDop());
            getAllApplicationDtos.add(getAllApplicationsDto);
        }
        return ResponseEntity.ok(getAllApplicationDtos);
    }
    @GetMapping("/oneapplications")
    private ResponseEntity <GetApplicationDto> GetOneApplication(@RequestParam Integer id){
        Application application = applicationRepository.findById(id).get();
            GetApplicationDto getAllApplicationsDto = new GetApplicationDto(application.getId(), application.getFirstName());
            getAllApplicationsDto.setOrganizationName(application.getOrganization().getOrganizationShortName());
            getAllApplicationsDto.setAddresses(application.getOrganization().getAddresses());
            getAllApplicationsDto.setServiceDopList(application.getServiceDop());
        return ResponseEntity.ok(getAllApplicationsDto);
    }

    private Application convertToApplication(ApplicationDto applicationDTO) {
        return modelMapper.map(applicationDTO, Application.class);
    }

    private ServiceDop convertToServiceDop(ServiceDopDto serviceDopDto) {
        return modelMapper.map(serviceDopDto, ServiceDop.class);
    }
    private AddressDto convertToAddressDto(Address address) {
        return modelMapper.map(address, AddressDto.class);
    }
}
