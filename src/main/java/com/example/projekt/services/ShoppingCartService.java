package com.example.projekt.services;

import com.example.projekt.exceptions.GameInStoreQuantityEqualsZeroException;
import com.example.projekt.models.*;
import com.example.projekt.repositories.OrderDetailRepository;
import com.example.projekt.repositories.OrderRepository;
import com.example.projekt.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ShoppingCartService
{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private HttpSession session;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private StoreService storeService;

    public List< OrderDetail > getCart ()
    {
        List< OrderDetail > cart = ( List< OrderDetail > ) this.session.getAttribute ( "cart" );

        if ( cart == null )
        {
            cart = new ArrayList<> ();
        }

        return cart;
    }

    public void addGameToCart ( Store store )
    {
        List< OrderDetail > cart = ( List< OrderDetail > ) session.getAttribute ( "cart" );

        if ( cart == null )
        {
            cart = new ArrayList<> ();
            cart.add ( new OrderDetail ( store.getGame (), 1 ) );
            session.setAttribute ( "cart", cart );
        }
        else
        {
            int index = exist ( store.getGame () );

            if ( index == -1 )
            {
                cart.add ( new OrderDetail ( store.getGame (), 1 ) );
            }
            else
            {
                int quantity = cart.get ( index )
                        .getQuantity () + 1;
                cart.get ( index )
                        .setQuantity ( quantity );
            }
            session.setAttribute ( "cart", cart );
        }
    }

    private int exist ( Game game )
    {
        List< OrderDetail > cart = getCart ();

        for ( int i = 0; i < cart.size (); ++i )
        {
            if ( cart.get ( i )
                    .getGame ()
                    .getId ()
                    .equals ( game.getId () ) )
            {
                return i;
            }
        }

        return -1;
    }

    public void removeGameFromCart ( Game game )
    {
        List< OrderDetail > cart = getCart ();
        int index = exist ( game );

        if ( index != -1 )
        {
            cart.remove ( index );
            session.setAttribute ( "cart", cart );
        }
    }

    public void removeGamesFromCart ( List< Game > games )
    {
        List< OrderDetail > cart = getCart ();
        for ( Game game : games )
        {
            int index = exist ( game );

            if ( index != -1 )
            {
                cart.remove ( index );
            }
        }
        session.setAttribute ( "cart", cart );
    }

    public void decreaseGameQuantityInCart ( Game game )
    {
        List< OrderDetail > cart = getCart ();
        int index = exist ( game );

        if ( index != -1 )
        {
            int quantity = cart.get ( index )
                    .getQuantity () - 1;

            if ( quantity <= 0 )
            {
                cart.remove ( index );
            }
            else
            {
                cart.get ( index )
                        .setQuantity ( quantity );
            }

            session.setAttribute ( "cart", cart );
        }
    }

    @Transactional
    public void buyGamesFromCart ( User user ) throws GameInStoreQuantityEqualsZeroException
    {
        Order order = new Order ( user );

        List< OrderDetail > cart = getCart ();

        boolean throwException = false;
        HashMap< Store, Integer > stores = new HashMap<> ();
        List< Game > toDelete = new ArrayList<> ();
        for ( OrderDetail o : cart )
        {
            Store store = this.storeService.findById ( o.getGame ()
                    .getId () );
            if ( store != null )
            {
                Integer quantity = o.getQuantity ();
                if ( quantity > store.getQuantity () )
                {
                    if ( store.getQuantity () == 0 )
                    {
                        toDelete.add ( o.getGame () );
                    }
                    else
                    {
                        int index = exist ( o.getGame () );
                        quantity = store.getQuantity ();
                        cart.get ( index )
                                .setQuantity ( quantity );

                        stores.put ( store, store.getQuantity () - quantity );
                    }
                    throwException = true;
                }
                else
                {
                    stores.put ( store, store.getQuantity () - quantity );
                }
            }
        }

        removeGamesFromCart ( toDelete );
        this.session.setAttribute ( "cart", cart );

        if ( throwException )
        {
            throw new GameInStoreQuantityEqualsZeroException ();
        }

        for ( Store store : stores.keySet () )
        {
            store.setQuantity ( stores.get ( store ) );
        }

        this.storeService.saveAll ( stores.keySet () );

        this.orderDetailRepository.saveAll ( cart );

        order.setOrderDetails ( new HashSet<> ( cart ) );
        order.setDate ( new Date () );
        order.setTotalValue ( getTotalValue () );
        order.setStatus ( this.statusRepository.findById ( 1 )
                .orElse ( null ) );

        this.orderRepository.saveAndFlush ( order );


        this.session.removeAttribute ( "cart" );
    }

    public BigDecimal getTotalValue ()
    {
        List< OrderDetail > cart = getCart ();

        BigDecimal totalValue = new BigDecimal ( 0.00 );

        for ( OrderDetail orderDetail : cart )
        {
            totalValue = totalValue.add ( orderDetail.getGame ()
                    .getPrice ()
                    .multiply ( new BigDecimal ( orderDetail.getQuantity () ) ) );
        }

        return totalValue;
    }
}
