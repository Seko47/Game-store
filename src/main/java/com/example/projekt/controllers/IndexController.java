package com.example.projekt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController
{

    @GetMapping ( path = "/" )
    public String home ()
    {
        return "index";
    }
}
