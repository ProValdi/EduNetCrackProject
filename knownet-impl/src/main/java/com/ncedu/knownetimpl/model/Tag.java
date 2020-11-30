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

  @JoinColumn(name = "FK_parentID")
  private Long parentID;

}
