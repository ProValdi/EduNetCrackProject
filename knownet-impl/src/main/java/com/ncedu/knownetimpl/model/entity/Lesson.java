package com.ncedu.knownetimpl.model.entity;



import com.ncedu.knownetimpl.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="lessons")
public class Lesson {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private User teacher;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private Tag tag;

    @Column(name = "topic")
    private String topic;

    @Column(name = "difficulty")
    private Double difficulty;

    @Column(name = "points_to_get")
    private Integer pointsToGet;

    @Column(name = "description")
    private String description;

    @Column(name = "skills_to_complete")
    private String skillsToComplete;

}
