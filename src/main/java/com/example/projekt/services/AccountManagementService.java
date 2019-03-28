package com.example.projekt.services;

import com.example.projekt.models.Role;
import com.example.projekt.models.User;
import com.example.projekt.repositories.RoleRepository;
import com.example.projekt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Service
public class AccountManagementService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public Page< User > findAll ( Pageable pageable )
    {
        return this.userRepository.findAll ( pageable );
    }

    public Page< User > findAllUsersUsingFilter ( String searchPhrase, Pageable pageable )
    {
        return this.userRepository.findAllUsersUsingFilter ( "%" + searchPhrase + "%", pageable );
    }

    public void hireUserAsEmployee ( User user )
    {
        user.setRoles ( new HashSet<> ( Arrays.asList ( this.roleRepository.findRoleByType ( Role.Types.ROLE_EMPLOYEE ), this.roleRepository.findRoleByType ( Role.Types.ROLE_USER ) ) ) );

        this.userRepository.save ( user );
    }

    public void fireUser ( User user )
    {
        user.setRoles ( new HashSet<> ( Collections.singletonList ( this.roleRepository.findRoleByType ( Role.Types.ROLE_USER ) ) ) );

        this.userRepository.save ( user );
    }

    public User findUserById ( Long id )
    {
        return this.userRepository.findById ( id )
                .orElse ( null );
    }

    public void updateUser ( User user, User userForm )
    {
        user.setCity ( userForm.getCity () );
        user.setPostCode ( userForm.getPostCode () );
        user.setStreet ( userForm.getStreet () );
        user.setHouseNumber ( userForm.getHouseNumber () );
        user.setApartmentNumber ( userForm.getApartmentNumber () );

        user.setName ( userForm.getName () );
        user.setSurname ( userForm.getSurname () );
        user.setPhoneNumber ( userForm.getPhoneNumber () );
        user.setCreditCardNumber ( userForm.getCreditCardNumber () );

        this.userRepository.saveAndFlush ( user );

    }
}
