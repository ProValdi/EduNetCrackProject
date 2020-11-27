package com.ncedu.knownetimpl.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "tags")
public class Tag {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "title", nullable = false, unique = true)
  private String title;

  @Column(name = "number_of_semestr")
  private int numberOfSemestr;

  @Column(name = "id_subject")
  private int idSubject;

  @Column(name = "level")
  private int level;

}
