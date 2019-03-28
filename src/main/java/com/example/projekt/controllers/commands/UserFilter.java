package com.example.projekt.controllers.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilter
{
    private String phrase;

    public void clear ()
    {
        this.phrase = "";
    }

    public boolean isEmpty ()
    {
        return ( this.phrase == null || this.phrase.isEmpty () );
    }
}
