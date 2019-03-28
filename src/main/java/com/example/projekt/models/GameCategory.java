package com.example.projekt.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table ( name = "game_categories" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameCategory implements Serializable
{
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column ( nullable = false )
    @NotBlank
    private String name;

    public GameCategory ( String category )
    {
        this.name = category;
    }
}
