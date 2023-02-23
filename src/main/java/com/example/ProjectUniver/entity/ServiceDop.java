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
    private Double price;

    @ManyToMany
    @JsonIgnore
    //@Fetch(FetchMode.JOIN)
    //@JoinTable(name = "application_serviceDop",
    //        joinColumns = @JoinColumn(name = "application_id"),
    //        inverseJoinColumns = @JoinColumn(name = "serviceDop_id"))
    
    private List<Application> applications;
}
