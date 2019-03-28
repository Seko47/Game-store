package com.example.projekt.services;

import com.example.projekt.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
    void save ( User user );

    boolean isLoginAvailable ( String username );

    boolean activateAccount ( String activationCode );
}
