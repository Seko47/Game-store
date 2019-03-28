package com.example.projekt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.http.HttpSession;

@EnableWebSecurity
@EnableGlobalMethodSecurity ( securedEnabled = true )
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Bean
    public PasswordEncoder passwordEncoder ()
    {
        return new BCryptPasswordEncoder ();
    }

    @Bean
    @Profile ( ProfileNames.IN_MEMORY )
    public UserDetailsService userDetailsService ()
    {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager ();
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder ();

        UserDetails user = userBuilder.username ( "user" )
                .password ( "user" )
                .roles ( "USER" )
                .build ();

        UserDetails admin = userBuilder.username ( "admin" )
                .password ( "admin" )
                .roles ( "ADMIN" )
                .build ();

        UserDetails test = userBuilder.username ( "test" )
                .password ( "test" )
                .roles ( "ADMIN", "USER" )
                .build ();

        manager.createUser ( user );
        manager.createUser ( admin );
        manager.createUser ( test );

        return manager;
    }

    @Override
    protected void configure ( HttpSecurity http ) throws Exception
    {
        http.authorizeRequests ()
                .antMatchers ( "/upload/**", "/statics/**", "/webjars/**", "/", "/gameList", "/registration", "/registration/success", "/registration/activate/*", "/gameList/*", "/shoppingCart", "/shoppingCart/add/*", "/shoppingCart/remove/*", "/shoppingCart/decrease/*" )
                .permitAll ()
                .antMatchers ( "/adminPanel/accountManagement" )
                .hasRole ( "ADMIN" )
                .antMatchers ( "/employeePanel/gameManagement" )
                .hasRole ( "EMPLOYEE" )
                .antMatchers ( "/shoppingCart/buy" )
                .hasRole ( "USER" )
                .anyRequest ()
                .authenticated ();
        http.formLogin ()
                .loginPage ( "/login" )
                .permitAll ();
        http.logout ()
                //.invalidateHttpSession ( false )
                .permitAll ();

        http.exceptionHandling ()
                .accessDeniedHandler ( createAccessDeniedHandler () );
    }

    private AccessDeniedHandler createAccessDeniedHandler ()
    {
        AccessDeniedHandlerImpl impl = new AccessDeniedHandlerImpl ();
        impl.setErrorPage ( "/accessDenied" );
        return impl;
    }
}
