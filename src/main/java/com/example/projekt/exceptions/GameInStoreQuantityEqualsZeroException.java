package com.example.projekt.exceptions;

public class GameInStoreQuantityEqualsZeroException extends Exception
{
    public GameInStoreQuantityEqualsZeroException ()
    {
        super ( "Twój koszyk został zaktualizowany" );
    }

    public GameInStoreQuantityEqualsZeroException ( String message )
    {
        super ( message );
    }
}
