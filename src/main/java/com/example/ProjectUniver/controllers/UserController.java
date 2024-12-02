package com.example.ProjectUniver.controllers;

import com.example.ProjectUniver.dto.ApplicationDto;
import com.example.ProjectUniver.dto.GetApplicationDto;
import com.example.ProjectUniver.dto.GetOrganizationDto;
import com.example.ProjectUniver.dto.UpdateDto;
import com.example.ProjectUniver.entity.Application;
import com.example.ProjectUniver.entity.Organization;
import com.example.ProjectUniver.entity.User;
import com.example.ProjectUniver.repository.ApplicationRepository;
import com.example.ProjectUniver.repository.OrganizationRepository;
import com.example.ProjectUniver.repository.UserRepository;
import com.example.ProjectUniver.service.ApplicationService;
import com.example.ProjectUniver.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Tag(name = "Актёр", description = "Все методы для работы с пользователем")
public class UserController {
    @Autowired
    UserService usersService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    OrganizationRepository organizationRepository;

    @GetMapping("/user")
    @Operation(summary = "Показать информацию о пользователе")
    public ResponseEntity<User> showInfoAboutUser(@AuthenticationPrincipal UserDetails userDetails) {
        User actor = userRepository.findUserByLogin(userDetails.getUsername());
        return ResponseEntity.ok(actor);
    }

//    @PatchMapping("/user/update")
//    @Operation(summary = "Обновление пользователя")
//    public ResponseEntity<Actor> updateUser(@RequestBody Actor actor, @AuthenticationPrincipal UserDetails userDetails){
//        Actor currentActor = usersService.findActorByLogin(userDetails.getUsername());
//        actor.setId(currentActor.getId());
//        if (!currentActor.getPassword().equals(actor.getPassword())) {
//            actor.setPassword(bCryptPasswordEncoder.encode(actor.getPassword()));
//        }
//        actorRepository.save(actor);
//        return ResponseEntity.ok(actor);
//    }
/*    @GetMapping("/applicationpage")
    private ResponseEntity <List<GetApplicationDto>>GetApplication(){
        List<Application> applicationList = applicationRepository.findAll();
        List<GetApplicationDto> getApplicationDtos = new ArrayList<>();
        for (Application application:applicationList
        ) {
            GetApplicationDto getApplicationDto = new GetApplicationDto(application.getId(),application.getDateApplication()
                    ,application.getAddInfo(), application.getFirstName());

            getApplicationDto.setServiceDopList(application.getServiceDop());
            getApplicationDtos.add(getApplicationDto);
        }
        return ResponseEntity.ok(getApplicationDtos);
    }*/

    @PatchMapping("/user/update")
    @Operation(summary = "Обновить информацию о пользователе")
    public ResponseEntity<User> updateUser(@RequestBody UpdateDto actorDto, @AuthenticationPrincipal UserDetails userDetails){
        User currentActor = userRepository.findUserByLogin(userDetails.getUsername());
        User newActor = usersService.update(actorDto, currentActor);
        return ResponseEntity.ok(newActor);
    }
}
