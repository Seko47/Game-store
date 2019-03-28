package com.example.projekt.services;

import com.example.projekt.models.Game;
import com.example.projekt.models.GameCategory;
import com.example.projekt.models.Store;
import com.example.projekt.repositories.GameCategoryRepository;
import com.example.projekt.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Service
public class GameManagementService
{
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private StoreService storeService;
    @Autowired
    private GameCategoryRepository gameCategoryRepository;
    @Autowired
    private FileUploadService fileUploadService;

    public void delete ( Game game )
    {
        Store store = this.storeService.findById ( game.getId () );
        if ( store != null )
        {
            store.setQuantity ( 0 );
            this.storeService.save ( store );
        }
    }

    public Page< Game > findAll ( Pageable pageable )
    {
        return this.gameRepository.findAll ( pageable );
    }

    public Page< Store > findAllGamesUsingFilter ( String phrase, BigDecimal priceMin, BigDecimal priceMax, Pageable pageable )
    {
        return this.storeService.findAllGamesInStoreUsingFilter ( phrase, priceMin, priceMax, true, pageable );
    }

    public void save ( Game game, MultipartFile[] files, Integer quantity, HttpServletRequest request )
    {
        List< String > filesURL = this.fileUploadService.uploadFiles ( files, request, game );

        game.setCoverURI ( ( filesURL.get ( 0 ) != null && !filesURL.get ( 0 )
                .isBlank () ? filesURL.get ( 0 ) : game.getCoverURI () ) );
        game.setImage1 ( ( filesURL.get ( 1 ) != null && !filesURL.get ( 1 )
                .isBlank () ? filesURL.get ( 1 ) : game.getImage1 () ) );
        game.setImage2 ( ( filesURL.get ( 2 ) != null && !filesURL.get ( 2 )
                .isBlank () ? filesURL.get ( 2 ) : game.getImage2 () ) );
        game.setImage3 ( ( filesURL.get ( 3 ) != null && !filesURL.get ( 3 )
                .isBlank () ? filesURL.get ( 3 ) : game.getImage3 () ) );

        this.gameRepository.save ( game );
        Store store = storeService.findById ( game.getId () );
        if ( store == null )
        {
            store = new Store ();
            store.setQuantity ( ( quantity != null && quantity > 0 ? quantity : 0 ) );
        }
        store.setGame ( game );
        this.storeService.save ( store );
    }

    public void changeQuantity ( Store store, int quantity )
    {
        if ( store != null )
        {
            store.setQuantity ( quantity );
            this.storeService.save ( store );
        }
    }

    public List< GameCategory > findAllGameCategories ()
    {
        return this.gameCategoryRepository.findAllByOrderById ();
    }
}
