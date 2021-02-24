package com.ncedu.knownetimpl.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requests")
public class LearnRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private User teacher;
    
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private User student;
    
    @ManyToOne()
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;
    
    @ColumnDefault("string default LESSON_REQUESTED")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.LESSON_REQUESTED;
    
    @ColumnDefault("boolean default false")
    @Column(name = "hidden_for_teacher")
    private Boolean hiddenForTeacher = false;
    
    @ColumnDefault("boolean default false")
    @Column(name = "hidden_for_student")
    private Boolean hiddenForStudent = false;
    
    public Boolean isAwaitingTeacher() {
        return status == Status.LESSON_REQUESTED;
    }
    
    public Boolean isAwaitingStudent() {
        return status == Status.LESSON_REQUEST_ACCEPTED;
    }
    
    
    public enum Status {
        LESSON_REQUESTED("Student requested for the lesson and waiting for teacher's reply"),
        LESSON_REQUEST_ACCEPTED("Teacher accepted student's request"),
        LESSON_REQUEST_REJECTED("Teacher rejected student's request"),
        MEETING_CONFIRMED("Student confirmed that the meeting with teacher had taken place"),
        MEETING_CANCELED("Student decided to cancel the meeting"),
        MEETING_DISPROVED("Student declared that the meeting with teacher had NOT taken place"),
        CONNECTION_FINISHED("Request cycle was fully processed");
        
        @Getter
        private final String description;
        
        Status(String description) {
            this.description = description;
        }
    }
}
