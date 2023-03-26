package com.example.ProjectUniver.service;

import com.example.ProjectUniver.config.jwt.JwtUtils;
import com.example.ProjectUniver.dto.*;
import com.example.ProjectUniver.entity.*;
import com.example.ProjectUniver.exception.GlobalException;
import com.example.ProjectUniver.repository.RoleRepository;
import com.example.ProjectUniver.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository actorRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrganizationService organizationService;


    @Override
    public JwtResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponseDto(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);
    }

    @Override
    public MessageResponse registration(RegistrationDto registrationDto) {
        if (actorRepository.existsByLogin(registrationDto.getUsername())) {
//            throw new GlobalException("Ошибка: Пользователь с таким логином уже зарегистрирован!", HttpStatus.BAD_REQUEST);
            return new MessageResponse("Ошибка: Пользователь с таким логином уже зарегистрирован!");
        }

        if (actorRepository.existsActorByEmail(registrationDto.getEmail())) {
//            throw new GlobalException("Ошибка: Пользователь с таким email уже зарегестрирован", HttpStatus.BAD_REQUEST);
            return new MessageResponse("Ошибка: Пользователь с таким email уже зарегистрирован!");

        }

        User user = new User(registrationDto.getEmail(),registrationDto.getUsername(), passwordEncoder.encode(registrationDto.getPassword()), registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getPatronymic(), registrationDto.getPhoneNumber());

        String requestRole = registrationDto.getRole();
        Set<Role> roles = new HashSet<>();

        switch (requestRole) {
            case "ROLE_USER":
                Role roleStudent = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new GlobalException("Роль 'Пользователь' не найдена", HttpStatus.BAD_REQUEST));
                roles.add(roleStudent);
                break;
            case "ROLE_ADMIN":
                Role roleTeacher = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new GlobalException("Роль 'Админ' не найдена", HttpStatus.BAD_REQUEST));
                roles.add(roleTeacher);
                break;
        }
        user.setRoles(roles);
        actorRepository.save(user);
        return new MessageResponse("Пользователь " + user.getLogin() + " успешно зарегистрирован!");
    }

    @Override
    public MessageResponse registration(RegistrationOrganizationDto registrationOrganizationDto) {
        if (actorRepository.existsByLogin(registrationOrganizationDto.getUsername())) {
//            throw new GlobalException("Ошибка: Пользователь с таким логином уже зарегистрирован!", HttpStatus.BAD_REQUEST);
            return new MessageResponse("Ошибка: Пользователь с таким логином уже зарегистрирован!");
        }

        if (actorRepository.existsActorByEmail(registrationOrganizationDto.getEmail())) {
//            throw new GlobalException("Ошибка: Пользователь с таким email уже зарегестрирован", HttpStatus.BAD_REQUEST);
            return new MessageResponse("Ошибка: Пользователь с таким email уже зарегистрирован!");

        }

        User user = new User(registrationOrganizationDto.getEmail(),registrationOrganizationDto.getUsername(), passwordEncoder.encode(registrationOrganizationDto.getPassword()), registrationOrganizationDto.getFirstName(),
                registrationOrganizationDto.getLastName(), registrationOrganizationDto.getPatronymic(), registrationOrganizationDto.getPhoneNumber());

        String requestRole = registrationOrganizationDto.getRole();
        Set<Role> roles = new HashSet<>();

        switch (requestRole) {
            case "ROLE_ORGANIZATION":
                Role roleStudent = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new GlobalException("Роль 'Пользователь' не найдена", HttpStatus.BAD_REQUEST));
                roles.add(roleStudent);
                break;
        }
        user.setRoles(roles);
        Organization organization = convertToOrganization(registrationOrganizationDto);
        AddressType addressType = convertToAddressType(registrationOrganizationDto.getAddressDto().getAddressTypeDto());
        Address address = convertToAddress(registrationOrganizationDto.getAddressDto());
        actorRepository.save(user);
        organization.setUser(user);
        organization.setApprove(false);
        organizationService.createOrganization(organization, address, addressType);
        user.setOrganization(organization);
        actorRepository.save(user);
        return new MessageResponse("Пользователь " + user.getLogin() + " успешно зарегистрирован!");
    }


    @Override
    public User findUserByLogin(String login) {
        return actorRepository.findUserByLogin(login);
    }

    @Override
    public User update(UpdateDto actorDto, User currentActor) {
        User newActor = convertToUser(actorDto);

        if (!actorDto.getLogin().equals(currentActor.getLogin())
                && actorRepository.existsByLogin(actorDto.getLogin())) {
            throw new GlobalException("Пользователь с таким login уже зарегистрирован", HttpStatus.BAD_REQUEST);
        }
        newActor.setId(currentActor.getId());
        newActor.setEmail(currentActor.getEmail());
        newActor.setPassword(currentActor.getPassword());
        actorRepository.save(newActor);
        return newActor;
    }



    private User convertToUser(UpdateDto actorDto) {
        return modelMapper.map(actorDto, User.class);
    }
    private Organization convertToOrganization(RegistrationOrganizationDto registrationOrganizationDto) {
        return modelMapper.map(registrationOrganizationDto, Organization.class);
    }

    private AddressType convertToAddressType(AddressTypeDto addressTypeDto) {
        return modelMapper.map(addressTypeDto, AddressType.class);
    }

    private Address convertToAddress(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }

}

