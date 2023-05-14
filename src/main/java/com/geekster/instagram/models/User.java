package com.geekster.instagram.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = User.class,property = "userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotBlank
    private String userFirstName;
    @NotBlank
    private String userLastName;

    @Column(unique = true,nullable = false)
    private String userName;

    @Email
    private String userEmail;

    private String userPassword;
    @Size(min = 10,max = 12)
    private String userPhoneNumber;
    @NotNull
    private Integer UserAge;

    public User(String userFirstName, String userLastName, String userName, String userEmail, String userPassword, String userPhoneNumber, Integer userAge) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        UserAge = userAge;
    }
    @OneToMany(mappedBy = "user")
    private List<Post>userPosts;
}
