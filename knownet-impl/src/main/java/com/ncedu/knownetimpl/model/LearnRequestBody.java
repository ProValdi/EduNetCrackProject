package com.ncedu.knownetimpl.model;

import com.ncedu.knownetimpl.model.entity.LearnRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearnRequestBody {
    private Long id;
    private Long studentId;
    private Long lessonId;
    private LearnRequest.Status status = LearnRequest.Status.LESSON_REQUESTED;
    private Boolean hiddenForTeacher = false;
    private Boolean hiddenForStudent = false;
}
