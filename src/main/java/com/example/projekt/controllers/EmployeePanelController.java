package com.example.projekt.controllers;

import com.example.projekt.controllers.commands.GameFilter;
import com.example.projekt.controllers.commands.OrderFilter;
import com.example.projekt.models.*;
import com.example.projekt.repositories.StatusRepository;
import com.example.projekt.services.GameManagementService;
import com.example.projekt.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

@Controller
@RequestMapping ( "/employeePanel" )
@SessionAttributes ( { "searchGameEmployeeCommand", "searchOrderEmployeeCommand" } )
public class EmployeePanelController
{
    @Autowired
    private GameManagementService gameManagementService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StatusRepository statusRepository;

    @Secured ( "ROLE_EMPLOYEE" )
    @GetMapping ( value = "/gameManagement/delete/{id}" )
    public String deleteGame ( @PathVariable ( name = "id" ) Game game )
    {
        this.gameManagementService.delete ( game );
        return "redirect:/employeePanel/gameManagement";
    }

    @Secured ( "ROLE_EMPLOYEE" )
    @GetMapping ( value = { "/gameManagement/edit/{id}", "/gameManagement/add" } )
    public String showGameForm ( Model model, @PathVariable ( name = "id", required = false ) Game game )
    {
        model.addAttribute ( "maxShortDescriptionLength", 300 );
        if ( game == null )
        {
            game = new Game ();
        }

        model.addAttribute ( "game", game );

        return "gameForm";
    }

    @Secured ( "ROLE_EMPLOYEE" )
    @PostMapping ( value = "/gameManagement/form" )
    public String processGameForm ( @Valid @ModelAttribute ( "game" ) Game game, BindingResult result, @RequestParam ( "files" ) MultipartFile[] files, @RequestParam ( value = "quantity", required = false ) Integer quantity, HttpServletRequest request )
    {
        if ( result.hasErrors () )
        {
            return "gameForm";
        }

        this.gameManagementService.save ( game, files, quantity, request );

        return "redirect:/employeePanel/gameManagement";
    }

    @Secured ( "ROLE_EMPLOYEE" )
    @RequestMapping ( value = "/gameManagement", method = { RequestMethod.GET, RequestMethod.POST } )
    public String showGameList ( Model model, Pageable pageable, @Valid @ModelAttribute ( "searchGameEmployeeCommand" ) GameFilter search, BindingResult result )
    {
        Page page = this.gameManagementService.findAllGamesUsingFilter ( search.getPhrase (), search.getPriceMin (), search
                .getPriceMax (), pageable );
        model.addAttribute ( "gameListPage", page );
        return "employeeGameManagement";
    }

    @Secured ( "ROLE_EMPLOYEE" )
    @GetMapping ( value = "/gameManagement", params = { "all" } )
    public String resetGameList ( @ModelAttribute ( "searchGameEmployeeCommand" ) GameFilter search )
    {
        search.clear ();
        return "redirect:/employeePanel/gameManagement";
    }

    @ModelAttribute ( "gameCategories" )
    public List< GameCategory > loadGameCategories ()
    {
        return this.gameManagementService.findAllGameCategories ();
    }

    @Secured ( "ROLE_EMPLOYEE" )
    @RequestMapping ( value = "/orderManagement", method = { RequestMethod.GET, RequestMethod.POST } )
    public String loadAllOrders ( Model model, Pageable pageable, @Valid @ModelAttribute ( "searchOrderEmployeeCommand" ) OrderFilter search, BindingResult result )
    {
        //pageable = pageable.first ();
        Page page;
        if ( search.getStatus ()
                .getId () != 0 )
        {
            page = this.orderService.findAllOrdersUsingFilter ( search.getStatus ()
                    .getId (), pageable );
        }
        else
        {
            page = this.orderService.findAllOrders ( pageable );
        }
        model.addAttribute ( "orderList", page );

        return "employeeOrderManagement";
    }

    @Secured ( "ROLE_EMPLOYEE" )
    @GetMapping ( value = "/orderManagement", params = { "all" } )
    public String resetOrderList ( @ModelAttribute ( "searchOrderEmployeeCommand" ) OrderFilter search )
    {
        search.clear ();
        return "redirect:/employeePanel/orderManagement";
    }

    @Secured ( "ROLE_EMPLOYEE" )
    @GetMapping ( value = "/orderManagement/accept/{id}" )
    public String acceptTheOrder ( @PathVariable ( "id" ) Order order )
    {
        this.orderService.acceptTheOrder ( order );

        return "redirect:/employeePanel/orderManagement";
    }

    @Secured ( "ROLE_EMPLOYEE" )
    @GetMapping ( value = "/orderManagement/{id}" )
    public String showOrderDetails ( Model model, @PathVariable ( name = "id" ) Order order )
    {
        if ( order == null )
        {
            return "redirect:/employeePanel/orderManagement";
        }
        model.addAttribute ( "order", order );

        return "orderDetails";
    }

    @Secured ( "ROLE_EMPLOYEE" )
    @GetMapping ( value = "/gameManagement/changeQuantity/{id}" )
    public String updateGameInStoreQuantity ( @PathVariable ( "id" ) Store store, HttpServletRequest request )
    {
        String message = request.getParameter ( "quantity" );

        try
        {
            int quantity = Integer.parseInt ( message );

            this.gameManagementService.changeQuantity ( store, quantity );
        }
        catch ( NumberFormatException e )
        {
            System.out.println ( "EmployeePanelController->updateGameInStoreQuantity: " + e.getMessage () );
        }

        return "redirect:/employeePanel/gameManagement";
    }

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        binder.addCustomFormatter ( new DateFormatter ( "yyyy-MM-dd" ) );

        DecimalFormat numberFormat = new DecimalFormat ( "#0.00" );
        numberFormat.setMaximumFractionDigits ( 2 );
        numberFormat.setMinimumFractionDigits ( 2 );
        numberFormat.setGroupingUsed ( false );
        CustomNumberEditor priceEditor = new CustomNumberEditor ( BigDecimal.class, numberFormat, true );
        binder.registerCustomEditor ( BigDecimal.class, "minPrice", priceEditor );
        binder.registerCustomEditor ( BigDecimal.class, "maxPrice", priceEditor );
    }

    @ModelAttribute ( "statuses" )
    public List< Status > loadStatuses ()
    {
        return this.statusRepository.findAll ();
    }


    @ModelAttribute ( "searchGameEmployeeCommand" )
    public GameFilter getGameSearchForEmployee ()
    {
        return new GameFilter ();
    }

    @ModelAttribute ( "searchOrderEmployeeCommand" )
    public OrderFilter getOrderSearchForEmployee ()
    {
        return new OrderFilter ();
    }

}
