package com.example.projekt.controllers.commands;

import com.example.projekt.models.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFilter
{
    private Status status;

    public boolean isEmpty ()
    {
        return ( this.status == null || this.status.getId () == 0 );
    }

    public void clear ()
    {
        this.status = new Status ( 0, "Wybierz status" );
    }

    public Status getStatus ()
    {
        if ( this.status == null )
        {
            clear ();
        }
        return this.status;
    }
}
