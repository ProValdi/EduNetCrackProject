package com.ncedu.knownetimpl.model;

import com.ncedu.knownetimpl.model.entity.LearnRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonBody {

    private Long id;

    private Long teacherId;

    private Long tagId;

    private String name;

    private String topic;

    private Double difficulty;

    private Integer pointsToGet;

    private String description;

    private String skillsToComplete;

}