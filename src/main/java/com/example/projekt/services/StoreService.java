package com.example.projekt.services;

import com.example.projekt.controllers.commands.GameFilter;
import com.example.projekt.models.Store;
import com.example.projekt.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class StoreService
{
    @Autowired
    private StoreRepository storeRepository;

    public Store findById ( Long id )
    {
        return this.storeRepository.findById ( id )
                .orElse ( null );
    }

    public void save ( Store store )
    {
        this.storeRepository.saveAndFlush ( store );
    }


    public Page< Store > findAllGamesInStoreUsingFilter ( String phrase, BigDecimal priceMin, BigDecimal priceMax, Boolean showAllGames, Pageable pageable )
    {
        return this.storeRepository.findAllGamesInStoreUsingFilter ( "%" + phrase + "%", priceMin, priceMax, ( showAllGames ? 0 : 1 ), pageable );
    }

    public void saveAll ( Set< Store > stores )
    {
        this.storeRepository.saveAll ( stores );
    }
}
