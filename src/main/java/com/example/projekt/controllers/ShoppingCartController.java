package com.example.projekt.controllers;

import com.example.projekt.exceptions.GameInStoreQuantityEqualsZeroException;
import com.example.projekt.exceptions.GameNotFoundException;
import com.example.projekt.models.Game;
import com.example.projekt.models.OrderDetail;
import com.example.projekt.models.Store;
import com.example.projekt.models.User;
import com.example.projekt.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping ( "/shoppingCart" )
public class ShoppingCartController
{
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping ( value = "" )
    public String showShoppingCart ( Model model )
    {
        model.addAttribute ( "cart", this.shoppingCartService.getCart () );
        model.addAttribute ( "totalValue", ( this.shoppingCartService.getTotalValue () ) );

        return "shoppingCart";
    }

    @GetMapping ( value = "/add/{id}" )
    public String addGameToShoppingCart ( @PathVariable ( "id" ) Store store ) throws GameNotFoundException, GameInStoreQuantityEqualsZeroException
    {
        if ( store == null )
        {
            throw new GameNotFoundException ();
        }

        if ( store.getQuantity () <= 0 )
        {
            throw new GameInStoreQuantityEqualsZeroException ( "Chwilowo w sklepie nie ma więcej egzemplarzy tej gry" );
        }

        this.shoppingCartService.addGameToCart ( store );

        return "redirect:/shoppingCart";
    }

    @GetMapping ( value = "/remove/{id}" )
    public String removeGameFromShoppingCart ( @PathVariable ( "id" ) Game game ) throws GameNotFoundException
    {
        if ( game == null )
        {
            throw new GameNotFoundException ();
        }

        this.shoppingCartService.removeGameFromCart ( game );

        return "redirect:/shoppingCart";
    }

    @GetMapping ( value = "/decrease/{id}" )
    public String decreaseGameQuantityInShoppingCart ( @PathVariable ( "id" ) Game game ) throws GameNotFoundException
    {
        if ( game == null )
        {
            throw new GameNotFoundException ();
        }

        this.shoppingCartService.decreaseGameQuantityInCart ( game );

        return "redirect:/shoppingCart";
    }

    @GetMapping ( value = "/buy" )
    public String buyGamesFromShoppingCart ( HttpSession session ) throws GameInStoreQuantityEqualsZeroException
    {
        List< OrderDetail > cart = ( List< OrderDetail > ) session.getAttribute ( "cart" );

        User user = ( User ) session.getAttribute ( "user" );

        if ( cart == null || cart.isEmpty () || user == null )
        {
            return "redirect:/shoppingCart";
        }

        this.shoppingCartService.buyGamesFromCart ( user );

        return "redirect:/orders";
    }
}
