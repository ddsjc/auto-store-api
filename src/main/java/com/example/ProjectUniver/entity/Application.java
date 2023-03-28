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
    private String firstName;
    private String dateApplication;
    private String addInfo;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "application_serviceDop",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "serviceDop_id"))
    private List<ServiceDop> serviceDop; //Sdes bil set vmesto list

    @OneToMany(mappedBy = "application")
    @JsonIgnore
    private List<User> user;
    @ManyToMany(mappedBy = "approveApplication")
    @JsonIgnore
    private List<User> approveUser;

    @ManyToOne
    @JsonIgnore
    private Organization organization;
}
