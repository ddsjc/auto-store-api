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

    @ManyToMany(mappedBy = "applications")
    @JsonIgnore
    private List<ServiceDop> serviceDops;

    @ManyToOne
    @JsonIgnore
    private User user;
}
