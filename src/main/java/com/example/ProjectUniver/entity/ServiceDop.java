package com.example.ProjectUniver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Table(name = "serviceDop")
@Entity
@Data
public class ServiceDop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String code;
    private String price;
    private String duration;
    private String addInfo;

   // @ManyToMany
   // @JsonIgnore
   // private Set<Application> applications;

    @ManyToOne
    @JsonIgnore
    private TypeOfService typeOfService;
}
