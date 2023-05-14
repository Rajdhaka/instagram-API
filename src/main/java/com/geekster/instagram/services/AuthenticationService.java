package com.geekster.instagram.services;

import com.geekster.instagram.models.AuthenticationToken;
import com.geekster.instagram.models.User;
import com.geekster.instagram.repositories.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    ITokenRepo tokenRepo;
    public void save(AuthenticationToken token) {
        tokenRepo.save(token);
    }

    public AuthenticationToken getToken(User user) {
       return tokenRepo.findByUser(user);
    }

    public boolean authenticate(String token, String userEmail) {

        AuthenticationToken authToken  = tokenRepo.findFirstByToken(token);

        User user  = authToken.getUser();

        String myEmail = user.getUserEmail();

        return myEmail.equals(userEmail);
    }
}
