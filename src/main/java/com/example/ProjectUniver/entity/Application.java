package com.example.ProjectUniver.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Table(name = "application")
@Entity
@Data
public class Application {
    public Application() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String dateApplication;
    private String addInfo;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "application_serviceDop",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "serviceDop_id"))
    private Set<ServiceDop> serviceDop;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    private Organization organization;
}
