package com.example.projekt.repositories;

import com.example.projekt.models.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface GameRepository extends JpaRepository< Game, Long >
{
    @Query ( "SELECT g FROM Game g WHERE (" +
            " UPPER(g.name) LIKE UPPER(:phrase) OR" +
            " UPPER(g.publisher) LIKE UPPER(:phrase) OR" +
            " UPPER(g.producer) LIKE UPPER(:phrase)) AND" +
            " (( (:min IS NULL OR " +
            ":min <= g.price) AND " +
            " (:max IS NULL OR :max >= g.price)))" +
            " ORDER BY g.id ASC" )
    Page< Game > findAllGamesUsingFilter ( @Param ( "phrase" ) String phrase, @Param ( "min" ) BigDecimal priceMin, @Param ( "max" ) BigDecimal priceMax, Pageable pageable );

}
