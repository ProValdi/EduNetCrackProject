package com.ncedu.knownetimpl.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "topic")
    private String topic;

    @Column(name = "difficulty")
    private Double difficulty;

    @Column(name = "group")
    private String group;

    @Column(name = "points_to_get")
    private Integer points_to_get;

    @Column(name = "description")
    private String description;

    @Column(name = "skills_to_complete")
    private String skills_to_complete;

//    private CourceSettings settings;
//    private Status status;
//    private Cource_Image photo;
}
