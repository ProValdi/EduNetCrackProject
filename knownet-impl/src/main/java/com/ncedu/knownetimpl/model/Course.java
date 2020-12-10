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


    @MapsId("mnemonic")
    @JoinColumn(referencedColumnName="id")
    private Long userId;

    //private Long tgaId;

    @Column(name = "name")
    private String name;

    @Column(name = "topic")
    private String topic;

    @Column(name = "difficulty")
    private Double difficulty;

    @Column(name = "points_to_get")
    private Integer points_to_get;

    @Column(name = "description")
    private String description;

    @Column(name = "skills_to_complete")
    private String skills_to_complete;


}
