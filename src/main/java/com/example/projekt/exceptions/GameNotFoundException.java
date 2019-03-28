package com.example.projekt.exceptions;

public class GameNotFoundException extends Exception
{
    public GameNotFoundException ()
    {
        super ( "Gra nie została znaleziona" );
    }
}
