package com.example.projekt.exceptions;

public class CannotBuyOrderAgainException extends Exception
{
    public CannotBuyOrderAgainException ()
    {
        super ( "W sklepie brakuje gier" );
    }
}
