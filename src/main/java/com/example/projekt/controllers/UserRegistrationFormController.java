package com.example.projekt.controllers;

import com.example.projekt.models.User;
import com.example.projekt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping ( "/registration" )
public class UserRegistrationFormController
{
    @Autowired
    private UserService userService;

    @GetMapping ( "" )
    public String registration ( Model model )
    {
        model.addAttribute ( "userCommand", new User () );

        return "registrationForm";
    }

    @PostMapping ( "" )
    public String registration ( @Valid @ModelAttribute ( "userCommand" ) User user, BindingResult bindingResult )
    {
        if ( bindingResult.hasErrors () )
        {
            return "registrationForm";
        }

        this.userService.save ( user );

        return "redirect:/registration/success";
    }

    @GetMapping ( "/success" )
    public String registrationSuccess ( Model model )
    {
        model.addAttribute ( "title", "Konto utworzone" );
        model.addAttribute ( "message", "Konto zostało zarejestrowane. Na adres e-mail wysłano wiadomość aktywacyjną, aby się zalogować aktywuj konto" );

        return "registrationSuccess";
    }

    @GetMapping ( "/activate/{code}" )
    public String registrationActivate ( Model model, @PathVariable ( "code" ) String activationCode )
    {
        if ( this.userService.activateAccount ( activationCode ) )
        {
            model.addAttribute ( "title", "Aktywacja powiodła się" );
            model.addAttribute ( "message", "Konto zostało aktywowane, możesz się zalogować" );
        }
        else
        {
            model.addAttribute ( "title", "Aktywacja nie udana" );
            model.addAttribute ( "message", "Nie udało się aktywować konta, możliwe, że konto już jest aktywne" );
        }

        return "registrationSuccess";
    }

    @InitBinder
    public void initBinder ( WebDataBinder webDataBinder )
    {
        webDataBinder.setDisallowedFields ( "enabled" );
    }
}
