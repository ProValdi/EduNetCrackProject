package com.ncedu.knownetimpl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonBody {
    private Long id;
    private Long teacherId;
    private Long tagId;
    private Long lessonId;
}