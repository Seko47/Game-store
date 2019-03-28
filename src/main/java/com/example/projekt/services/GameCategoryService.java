package com.example.projekt.services;

import com.example.projekt.models.GameCategory;
import com.example.projekt.repositories.GameCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GameCategoryService
{
    @Autowired
    private GameCategoryRepository gameCategoryRepository;

    public Page< GameCategory > findAll ( Pageable pageable )
    {
        return this.gameCategoryRepository.findAllByOrderById ( pageable );
    }

    public void save ( GameCategory gameCategory )
    {
        this.gameCategoryRepository.save ( gameCategory );
    }
}
