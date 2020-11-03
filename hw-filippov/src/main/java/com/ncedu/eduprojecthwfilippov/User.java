package com.ncedu.eduprojecthwfilippov;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String login;
    private String encodedPassword;
    private String firstName;
    private String lastName;
    private Double rating;
    private Integer points;
    private Integer additionalBuyingCost;
    private Integer additionalSellingCost;
    private String group;
    private Contacts contacts;
//    private UserSettings settings;
//    private Status status;
//    private Image photo;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Contacts {
        private String email;
        private String vkLink;
        private String telegramLink;
        private String phoneNumber;
    }
}
