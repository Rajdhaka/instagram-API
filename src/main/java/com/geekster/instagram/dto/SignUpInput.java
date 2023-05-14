package com.geekster.instagram.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInput {
    private String userEmail;

    private String userPhoneNumber;

    private String userFirstName;

    private String userLastName;

    private String userName;

    private String userPassword;

    private String userAge;

}
