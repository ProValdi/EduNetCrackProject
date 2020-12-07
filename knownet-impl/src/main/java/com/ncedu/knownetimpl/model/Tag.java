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

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "parent_id")
  private Long parentID;

}
