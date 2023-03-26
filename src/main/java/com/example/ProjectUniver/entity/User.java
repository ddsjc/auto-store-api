package com.example.ProjectUniver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String phoneNumber;

    private String email;

    private String login;

    private String password;

    private String addInfo;

    private boolean status;

    private boolean isMailing;


    private String resetPasswordToken;


    public User() {
    }

    public User(String email,
                  String login, String password,
                 String firstName, String lastName, String patronymic, String phoneNumber) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany (mappedBy = "user" )
    @JsonIgnore
    private List<Application> applications;

    @OneToOne
    @JsonIgnore
    private Organization organization;
}

