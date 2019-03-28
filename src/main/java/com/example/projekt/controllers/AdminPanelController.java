package com.example.projekt.controllers;

import com.example.projekt.controllers.commands.UserFilter;
import com.example.projekt.models.Game;
import com.example.projekt.models.GameCategory;
import com.example.projekt.models.Role;
import com.example.projekt.models.User;
import com.example.projekt.services.AccountManagementService;
import com.example.projekt.services.GameCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping ( "/adminPanel" )
public class AdminPanelController
{
    @Autowired
    private AccountManagementService accountManagementService;
    @Autowired
    private GameCategoryService gameCategoryService;

    @Secured ( "ROLE_ADMIN" )
    @RequestMapping ( value = "/accountManagement", method = { RequestMethod.GET, RequestMethod.POST } )
    public String showUserList ( Model model, Pageable pageable, @Valid @ModelAttribute ( "searchUser" ) UserFilter search, BindingResult result )
    {
        Page page;
        if ( search.isEmpty () )
        {
            page = this.accountManagementService.findAll ( pageable );
        }
        else
        {
            //pageable = pageable.first ();
            page = this.accountManagementService.findAllUsersUsingFilter ( search.getPhrase (), pageable );
        }
        model.addAttribute ( "userCommand", page );

        return "adminAccountManagement";
    }

    @Secured ( "ROLE_ADMIN" )
    @GetMapping ( value = "/accountManagement/hire/{id}" )
    public String hireUser ( @PathVariable ( name = "id" ) User user )
    {
        for ( Role role : user.getRoles () )
        {
            if ( role.getType () == Role.Types.ROLE_ADMIN )
            {
                return "redirect:/adminPanel/accountManagement";
            }
        }
        this.accountManagementService.hireUserAsEmployee ( user );

        return "redirect:/adminPanel/accountManagement";

    }

    @Secured ( "ROLE_ADMIN" )
    @GetMapping ( value = "/accountManagement/fire/{id}" )
    public String fireUser ( @PathVariable ( name = "id" ) User user )
    {
        for ( Role role : user.getRoles () )
        {
            if ( role.getType () == Role.Types.ROLE_ADMIN )
            {
                return "redirect:/adminPanel/accountManagement";
            }
        }
        this.accountManagementService.fireUser ( user );

        return "redirect:/adminPanel/accountManagement";
    }

    //TODO dodawanie nowych kategorii gier
    @Secured ( "ROLE_ADMIN" )
    @RequestMapping ( value = "/gameCategoriesManagement", method = { RequestMethod.GET, RequestMethod.POST } )
    public String showGameCategoriesList ( Model model, Pageable pageable )
    {
        Page page = this.gameCategoryService.findAll ( pageable );

        model.addAttribute ( "gameCategories", page );

        return "adminGameCategoryManagement";
    }

    @Secured ( "ROLE_ADMIN" )
    @GetMapping ( value = { "/gameCategoriesManagement/edit/{id}", "/gameCategoriesManagement/add" } )
    public String editGameCategory ( Model model, @PathVariable ( name = "id", required = false ) GameCategory gameCategory )
    {
        if ( gameCategory == null )
        {
            gameCategory = new GameCategory ();
        }

        model.addAttribute ( "gameCategory", gameCategory );

        return "adminGameCategoryForm";
    }

    @Secured ( "ROLE_ADMIN" )
    @PostMapping ( value = "/gameCategoriesManagement/save" )
    public String saveGameCategory ( @Valid @ModelAttribute ( "gameCategory" ) GameCategory gameCategory, BindingResult result )
    {
        if(result.hasErrors ())
        {
            return "adminGameCategoryForm";
        }
        this.gameCategoryService.save ( gameCategory );

        return "redirect:/adminPanel/gameCategoriesManagement";
    }
}
