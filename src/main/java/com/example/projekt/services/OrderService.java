package com.example.projekt.services;

import com.example.projekt.exceptions.CannotBuyOrderAgainException;
import com.example.projekt.models.*;
import com.example.projekt.repositories.OrderRepository;
import com.example.projekt.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private HttpSession session;

    @Autowired
    private StoreService storeService;

    public Page< Order > findUserOrders ( Pageable pageable )
    {
        User user = ( User ) this.session.getAttribute ( "user" );

        return this.orderRepository.findAllOrdersByUsername ( user.getUsername (), pageable );
    }

    public Page< Order > findUserOrdersUsingFilter ( int status, Pageable pageable )
    {
        User user = ( User ) this.session.getAttribute ( "user" );

        return this.orderRepository.findAllOrdersByUsernameUsingFilter ( status, user.getUsername (), pageable );
    }

    public Page< Order > findAllOrdersUsingFilter ( int status, Pageable pageable )
    {
        return this.orderRepository.findAllOrdersForEmployeeUsingFilter ( status, pageable );
    }

    public void cancelTheOrder ( Order order )
    {
        if ( order.getStatus ()
                .getId () == 1 && ( ( User ) this.session.getAttribute ( "user" ) ).getUsername ()
                .equalsIgnoreCase ( order.getUser ()
                        .getUsername () ) )
        {
            Status status = this.statusRepository.findById ( 3 )
                    .orElse ( null );

            if ( status != null )
            {
                order.setStatus ( status );

                for ( OrderDetail o : order.getOrderDetails () )
                {
                    Store store = this.storeService.findById ( o.getGame ()
                            .getId () );

                    if ( store != null )
                    {
                        store.setQuantity ( store.getQuantity () + o.getQuantity () );
                        this.storeService.save ( store );
                    }
                }

                this.orderRepository.saveAndFlush ( order );
            }
        }
    }

    public void acceptTheOrder ( Order order )
    {
        if ( order.getStatus ()
                .getId () == 1 )
        {
            Status status = this.statusRepository.findById ( 2 )
                    .orElse ( null );

            if ( status != null )
            {
                order.setStatus ( status );
                this.orderRepository.saveAndFlush ( order );
            }
        }
    }

    @Transactional
    public void buyTheOrder ( Order order ) throws CannotBuyOrderAgainException
    {
        if ( order.getStatus ()
                .getId () == 3 && ( ( User ) this.session.getAttribute ( "user" ) ).getUsername ()
                .equalsIgnoreCase ( order.getUser ()
                        .getUsername () ) )
        {
            Status status = this.statusRepository.findById ( 1 )
                    .orElse ( null );

            if ( status != null )
            {
                for ( OrderDetail o : order.getOrderDetails () )
                {
                    Store store = this.storeService.findById ( o.getGame ()
                            .getId () );

                    if ( store != null )
                    {
                        int quantity = store.getQuantity () - o.getQuantity ();
                        if ( quantity < 0 )
                        {
                            throw new CannotBuyOrderAgainException ();
                        }
                        store.setQuantity ( quantity );
                        this.storeService.save ( store );
                    }
                }

                order.setStatus ( status );
                this.orderRepository.saveAndFlush ( order );
            }
        }
    }

    public Page< Order > findAllOrders ( Pageable pageable )
    {
        return this.orderRepository.findAllOrdersForEmployee ( pageable );
    }
}
