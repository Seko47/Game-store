package com.example.projekt.repositories;

import com.example.projekt.models.GameCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameCategoryRepository extends JpaRepository< GameCategory, Integer >
{
    Page< GameCategory > findAllByOrderById ( Pageable pageable );

    List< GameCategory > findAllByOrderById ();
    //GameCategory findGameCategoryByName ( String category );
}
