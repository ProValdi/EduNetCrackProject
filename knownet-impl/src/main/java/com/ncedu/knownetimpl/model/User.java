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
    @GeneratedValue
    private Long id;

    @Column(name = "login", nullable = false, length = 100)
    private String login;
//    private String encodedPassword;
//    private String firstName;
//    private String lastName;
//    private Double rating;
//    private Integer points;
//    private Integer additionalBuyingCost;
//    private Integer additionalSellingCost;
//    private String group;
//    private Contacts contacts;
//    private UserSettings settings;
//    private Status status;
//    private Image photo;


//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class Contacts {
//        private String email;
//        private String vkLink;
//        private String telegramLink;
//        private String phoneNumber;
//    }
}
