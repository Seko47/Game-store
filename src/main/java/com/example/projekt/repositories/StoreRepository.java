package com.example.projekt.repositories;

import com.example.projekt.models.Game;
import com.example.projekt.models.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface StoreRepository extends JpaRepository< Store, Long >, JpaSpecificationExecutor< Store >
{
    @Query ( "SELECT s FROM Store s WHERE (" +
            " UPPER(s.game.name) LIKE UPPER(:phrase) OR" +
            " UPPER(s.game.publisher) LIKE UPPER(:phrase) OR" +
            " UPPER(s.game.producer) LIKE UPPER(:phrase)) AND" +
            " (( (:min IS NULL OR" +
            " :min <= s.game.price) AND " +
            " (:max IS NULL OR :max >= s.game.price))) AND" +
            " (s.quantity >= :quantity)" +
            " ORDER BY s.game.name ASC" )
    Page< Store > findAllGamesInStoreUsingFilter ( @Param ( "phrase" ) String phrase, @Param ( "min" ) BigDecimal priceMin, @Param ( "max" ) BigDecimal priceMax, @Param ( "quantity" ) Integer quantity, Pageable pageable );

}
