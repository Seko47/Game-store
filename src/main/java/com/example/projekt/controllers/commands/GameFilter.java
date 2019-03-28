package com.example.projekt.controllers.commands;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class GameFilter
{
    private String phrase;

    @PositiveOrZero
    @NumberFormat ( style = NumberFormat.Style.CURRENCY )
    private BigDecimal priceMin;

    @PositiveOrZero
    @NumberFormat ( style = NumberFormat.Style.CURRENCY )
    private BigDecimal priceMax;

    private Boolean allGames = false;

    public void clear ()
    {
        this.phrase = "";
        this.priceMin = null;
        this.priceMax = null;
        this.allGames = false;
    }

    public boolean isEmpty ()
    {
        return ( this.phrase == null || this.phrase.isEmpty () || this.phrase.isBlank () ) && this.priceMin == null && this.priceMax == null;
    }

    public String getPhrase ()
    {
        if ( phrase == null )
        {
            this.phrase = "";
        }
        return this.phrase.trim ();
    }
}
