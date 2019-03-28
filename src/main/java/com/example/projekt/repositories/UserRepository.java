package com.example.projekt.repositories;

import com.example.projekt.models.Game;
import com.example.projekt.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface UserRepository extends JpaRepository< User, Long >
{
    User findUserByUsername ( String username );

    User findUserByUsernameAndEnabled ( String username, Boolean enabled );

    User findUserByActivationCode ( String activationCode );

    @Query ( "SELECT u FROM User u WHERE (" +
            " UPPER(u.username) LIKE UPPER(:phrase) OR" +
            " UPPER(u.name) LIKE UPPER(:phrase) OR" +
            " upper(u.surname) LIKE UPPER(:phrase) OR" +
            " UPPER(CONCAT(u.name, CONCAT(' ', u.surname) ) ) LIKE UPPER(:phrase) )" )
    Page< User > findAllUsersUsingFilter ( @Param ( "phrase" ) String phrase, Pageable pageable );
}
