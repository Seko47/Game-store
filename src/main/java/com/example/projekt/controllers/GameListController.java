package com.example.projekt.controllers;

import com.example.projekt.controllers.commands.GameFilter;
import com.example.projekt.exceptions.GameNotFoundException;
import com.example.projekt.models.Store;
import com.example.projekt.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@Controller
@RequestMapping ( "/gameList" )
@SessionAttributes ( { "searchCommand" } )
public class GameListController
{
    @Autowired
    private StoreService storeService;

    @GetMapping ( value = "/{id}" )
    public String showGameDetails ( Model model, @PathVariable ( name = "id" ) Store store ) throws GameNotFoundException
    {
        if ( store == null )
        {
            throw new GameNotFoundException ();
        }
        model.addAttribute ( "store", store );
        return "gameDetails";
    }

    @RequestMapping ( value = "", method = { RequestMethod.GET, RequestMethod.POST } )
    public String showGameList ( Model model, Pageable pageable, @Valid @ModelAttribute ( "searchCommand" ) GameFilter search, BindingResult result )
    {
        Page page = this.storeService.findAllGamesInStoreUsingFilter ( search.getPhrase (), search.getPriceMin (), search
                .getPriceMax (), search.getAllGames (), pageable );

        model.addAttribute ( "gameListPage", page );
        return "gameList";
    }

    @ModelAttribute ( "searchCommand" )
    public GameFilter getSimpleSearch ()
    {
        return new GameFilter ();
    }

    @GetMapping ( value = "", params = { "all" } )
    public String resetGameList ( @ModelAttribute ( "searchCommand" ) GameFilter search )
    {
        search.clear ();
        return "redirect:gameList";
    }

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        DecimalFormat numberFormat = new DecimalFormat ( "#0.00" );
        numberFormat.setMaximumFractionDigits ( 2 );
        numberFormat.setMinimumFractionDigits ( 2 );
        numberFormat.setGroupingUsed ( false );
        CustomNumberEditor priceEditor = new CustomNumberEditor ( BigDecimal.class, numberFormat, true );
        binder.registerCustomEditor ( BigDecimal.class, "minPrice", priceEditor );
        binder.registerCustomEditor ( BigDecimal.class, "maxPrice", priceEditor );
    }
}
