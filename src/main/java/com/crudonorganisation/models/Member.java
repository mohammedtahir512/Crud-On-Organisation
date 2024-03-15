package com.crudonorganisation.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @JsonIgnore
    private double salary;
    @JsonIgnore
    private int age;
    @JsonIgnore
    private String joiningDate;
    @ManyToOne
    @JsonBackReference
    private Team team;
    @ManyToOne
    private Employee reportsTo;
    private String role;
    private int totalHoursWorked;
    private int totalWorkload;

}
