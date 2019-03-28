package com.example.projekt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Table ( name = "order_details" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable
{
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotNull
    @ManyToOne ( fetch = FetchType.EAGER, optional = false )
    @JoinColumn ( name = "game_id", nullable = false )
    private Game game;

    @Positive
    @NotNull
    private Integer quantity;

    public OrderDetail ( Game game, int quantity )
    {
        this.game = game;
        this.quantity = quantity;
    }
}
