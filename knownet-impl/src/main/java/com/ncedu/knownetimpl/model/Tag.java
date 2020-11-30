package com.ncedu.knownetimpl.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {

  @Id
  @GeneratedValue
  private Long id;

  //todo сделать так же как в таблице базы данных
  @Column(name = "title", nullable = false, unique = true)
  private String title;

  //???
  @Column(name = "parent_id")
  private Long parentID;

}
