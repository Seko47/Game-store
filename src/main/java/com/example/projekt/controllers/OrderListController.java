package com.example.projekt.controllers;

import com.example.projekt.controllers.commands.OrderFilter;
import com.example.projekt.exceptions.CannotBuyOrderAgainException;
import com.example.projekt.models.Order;
import com.example.projekt.models.Status;
import com.example.projekt.models.User;
import com.example.projekt.repositories.StatusRepository;
import com.example.projekt.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping ( "/orders" )
public class OrderListController
{
    @Autowired
    private OrderService orderService;
    @Autowired
    private StatusRepository statusRepository;

    @Secured ( "ROLE_USER" )
    @RequestMapping ( value = "", method = { RequestMethod.GET, RequestMethod.POST } )
    public String showOrderList ( Model model, Pageable pageable, @Valid @ModelAttribute ( "searchCommand" ) OrderFilter search, BindingResult result )
    {
        //pageable = pageable.first ();

        Page page;
        if ( search.getStatus ()
                .getId () != 0 )
        {
            page = this.orderService.findUserOrdersUsingFilter ( search.getStatus ()
                    .getId (), pageable );
        }
        else
        {
            page = this.orderService.findUserOrders ( pageable );
        }

        model.addAttribute ( "orders", page );

        return "orders";
    }

    @Secured ( "ROLE_USER" )
    @GetMapping ( value = "", params = { "all" } )
    public String resetOrderList ( @ModelAttribute ( "searchCommand" ) OrderFilter search )
    {
        search.clear ();
        return "redirect:/orders";
    }

    @Secured ( "ROLE_USER" )
    @GetMapping ( value = "/cancel/{id}" )
    public String cancelTheOrder ( @PathVariable ( "id" ) Order order )
    {
        this.orderService.cancelTheOrder ( order );

        return "redirect:/orders";
    }

    @Secured ( "ROLE_USER" )
    @GetMapping ( value = "/buy/{id}" )
    public String buyTheOrder ( @PathVariable ( "id" ) Order order ) throws CannotBuyOrderAgainException
    {
        this.orderService.buyTheOrder ( order );

        return "redirect:/orders";
    }

    @Secured ( "ROLE_USER" )
    @GetMapping ( value = "/{id}" )
    public String showOrderDetails ( Model model, @PathVariable ( name = "id" ) Order order, HttpSession session )
    {
        if ( order == null || !order.getUser ()
                .getId ()
                .equals ( ( ( User ) session.getAttribute ( "user" ) ).getId () ) )
        {
            return "redirect:/orders";
        }
        model.addAttribute ( "order", order );

        return "orderDetails";
    }

    @ModelAttribute ( "statuses" )
    public List< Status > loadStatuses ()
    {
        return this.statusRepository.findAll ();
    }
}
