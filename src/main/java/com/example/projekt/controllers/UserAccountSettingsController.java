package com.example.projekt.controllers;

import com.example.projekt.models.User;
import com.example.projekt.services.AccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping ( "/account" )
public class UserAccountSettingsController
{
    @Autowired
    private AccountManagementService accountManagementService;

    @Secured ( { "ROLE_USER" } )
    @GetMapping ( "" )
    public String showUserSettings ( Model model, HttpSession session, HttpServletRequest request )
    {
        String saved = request.getParameter ( "saved" );

        if ( saved != null )
        {
            model.addAttribute ( "infoAlert", "Zapisano zmiany" );
        }

        User user = ( User ) session.getAttribute ( "user" );
        if ( user == null )
        {
            return "redirect:/login";
        }

        model.addAttribute ( "userCommand", user );

        return "userAccountSettings";
    }

    @PostMapping ( "" )
    public String updateAccount ( @Valid @ModelAttribute ( "userCommand" ) User userForm, BindingResult bindingResult, HttpSession session )
    {
        if ( bindingResult.hasErrors () )
        {
            return "userAccountSettings";
        }

        User user = this.accountManagementService.findUserById ( ( ( User ) session.getAttribute ( "user" ) ).getId () );

        if ( user == null )
        {
            return "redirect:/login";
        }

        this.accountManagementService.updateUser ( user, userForm );

        session.setAttribute ( "user", user );

        return "redirect:/account?saved";
    }
}