package com.example.projekt.repositories;

import com.example.projekt.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository< Order, Long >
{
    @Query ( "SELECT o FROM Order o WHERE (" +
            " UPPER( o.user.username) LIKE UPPER(:username))" +
            " ORDER BY o.date DESC" )
    Page< Order > findAllOrdersByUsername ( @Param ( "username" ) String username, Pageable pageable );

    @Query ( "SELECT o FROM Order o WHERE o.status.id = :statusId" +
            " ORDER BY o.date DESC" )
    Page< Order > findAllOrdersForEmployeeUsingFilter ( int statusId, Pageable pageable );

    @Query ( "SELECT o FROM Order o" +
            " ORDER BY o.date DESC" )
    Page< Order > findAllOrdersForEmployee ( Pageable pageable );

    @Query ( "SELECT o FROM Order o WHERE (" +
            " UPPER( o.user.username) LIKE UPPER(:username))" +
            " AND o.status.id = :statusId" +
            " ORDER BY o.date DESC" )
    Page< Order > findAllOrdersByUsernameUsingFilter ( int statusId, String username, Pageable pageable );
}
