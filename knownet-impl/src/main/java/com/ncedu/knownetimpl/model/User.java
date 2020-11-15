package com.ncedu.knownetimpl.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
//    @GeneratedValue
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;
    
    @Column(name = "encoded_password")
    private String encodedPassword;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "rating")
    private Double rating;
    
    @Column(name = "points")
    private Integer points;
    
//    private Integer additionalBuyingCost;
//    private Integer additionalSellingCost;
    
    @Column(name = "group_label")
    private String group;
    
//    private UserSettings settings;
//    private Status status;
//    private Image photo;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "vk_link")
    private String vkLink;
    
    @Column(name = "telegram_link")
    private String telegramLink;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
}
