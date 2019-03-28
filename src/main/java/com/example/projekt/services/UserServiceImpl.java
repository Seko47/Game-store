package com.example.projekt.services;

import com.example.projekt.config.ProfileNames;
import com.example.projekt.models.Role;
import com.example.projekt.repositories.RoleRepository;
import com.example.projekt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service ( value = "userDetailsService" )
@Profile ( ProfileNames.DATABASE )
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HttpSession session;
    @Autowired
    private EmailService emailService;

    @Override
    @Transactional ( readOnly = true )
    public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException
    {
        com.example.projekt.models.User user = this.userRepository.findUserByUsernameAndEnabled ( username, true );

        if ( user == null )
        {
            throw new UsernameNotFoundException ( username );
        }

        this.session.setAttribute ( "user", user );

        return convertToUserDetails ( user );
    }

    private UserDetails convertToUserDetails ( com.example.projekt.models.User user )
    {
        user.setEnabled ( false );
        Set< GrantedAuthority > grantedAuthorities = new HashSet<> ();
        for ( Role role : user.getRoles () )
        {
            grantedAuthorities.add ( new SimpleGrantedAuthority ( role.getType ()
                    .toString () ) );
        }

        return new User ( user.getUsername (), user.getPassword (), grantedAuthorities );
    }

    @Override
    public void save ( com.example.projekt.models.User user )
    {
        user.setRoles ( new HashSet<> ( Arrays.asList ( this.roleRepository.findRoleByType ( Role.Types.ROLE_USER ) ) ) );
        user.setPassword ( this.passwordEncoder.encode ( user.getPassword () ) );
        user.setPasswordConfirm ( null );
        user.setActivationCode ( getActivationCode () );

        this.emailService.sendActivationMail ( user.getEmail (), user.getName () + " " + user.getSurname (), user.getActivationCode () );

        this.userRepository.saveAndFlush ( user );
    }

    private String getActivationCode ()
    {
        Long code;
        do
        {
            code = 10000L + ( long ) ( Math.random () * ( 999999999999L ) );
        } while ( this.userRepository.findUserByActivationCode ( code.toString () ) != null );

        return code.toString ();
    }

    @Override
    public boolean activateAccount ( String activationCode )
    {
        com.example.projekt.models.User user = this.userRepository.findUserByActivationCode ( activationCode );

        if ( user == null )
        {
            return false;
        }

        user.setEnabled ( true );
        user.setActivationCode ( "" );

        this.userRepository.saveAndFlush ( user );

        return true;
    }

    @Override
    public boolean isLoginAvailable ( String username )
    {
        return this.userRepository.findUserByUsername ( username ) == null;
    }
}
