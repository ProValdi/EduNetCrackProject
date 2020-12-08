package com.ncedu.knownetimpl.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requests")
public class LearnRequest {
    @Id
    @GeneratedValue
    private Long id;
    
    ////
    @Column(name = "teacher_id")
    private Long teacherId;
    
    ////
    @Column(name = "student_id")
    private Long studentId;
    
    ////
    @Column(name = "lesson_id")
    private Long lessonId;
    
    @Column(name = "status")
    private Status status;
    
    @Column(name = "awaiting_teacher")
    private Boolean awaitingTeacher;
    
    @Column(name = "hidden_for_teacher")
    private Boolean hiddenForTeacher;

    @Column(name = "hidden_for_student")
    private Boolean hiddenForStudent;
    
    
    public enum Status {
        LESSON_REQUESTED(""),
        LESSON_REQUEST_ACCEPTED(""),
        LESSON_REQUEST_REJECTED(""),
        MEETING_CONFIRMED(""),
        MEETING_DISMISSED(""),
        CONNECTION_FINISHED("");
        
        @Getter
        private final String description;
        
        Status(String description) {
            this.description = description;
        }
    }
}
