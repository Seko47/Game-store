package com.example.projekt.controllers;

import com.example.projekt.exceptions.CannotBuyOrderAgainException;
import com.example.projekt.exceptions.GameInStoreQuantityEqualsZeroException;
import com.example.projekt.exceptions.GameNotFoundException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyControllerAdvice
{
    @ResponseStatus ( HttpStatus.NOT_FOUND )
    @ExceptionHandler ( { GameNotFoundException.class } )
    public String handleGameNotFoundError ( Model model, HttpServletRequest httpServletRequest, Exception e )
    {
        model.addAttribute ( "exception", e );
        model.addAttribute ( "url", httpServletRequest.getRequestURL () );

        return "errors/gameNotFound";
    }

    @ResponseStatus ( HttpStatus.NOT_FOUND )
    @ExceptionHandler ( { CannotBuyOrderAgainException.class } )
    public String handleCannotBuyOrderAgainError ( Model model, HttpServletRequest httpServletRequest, Exception e )
    {
        model.addAttribute ( "exception", e );

        return "errors/orderIncomplete";
    }

    @ResponseStatus ( HttpStatus.NOT_FOUND )
    @ExceptionHandler ( { GameInStoreQuantityEqualsZeroException.class } )
    public String handleGameInStoreNotFoundError ( Model model, HttpServletRequest httpServletRequest, Exception e )
    {
        model.addAttribute ( "exception", e );

        return "errors/gameInStoreNotFound";
    }

    @ExceptionHandler ( { JDBCConnectionException.class, DataIntegrityViolationException.class } )
    public String handleDatabaseError ( Model model, HttpServletRequest httpServletRequest, Exception e )
    {
        model.addAttribute ( "exception", e );
        model.addAttribute ( "url", httpServletRequest.getRequestURL () );

        return "errors/dbError";
    }

    @ExceptionHandler ( EntityNotFoundException.class )
    public String handleEntityNotFoundError ( Model model, HttpServletRequest httpServletRequest, Exception e )
    {
        model.addAttribute ( "exception", e );
        model.addAttribute ( "url", httpServletRequest.getRequestURL () );

        return "errors/entityNotFound";
    }
}
