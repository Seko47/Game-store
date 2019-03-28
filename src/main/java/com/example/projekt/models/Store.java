package com.example.projekt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity
@Table ( name = "games_in_store" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store implements Serializable
{
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @OneToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "id" )
    private Game game;

    @Column ( nullable = false )
    @PositiveOrZero
    private Integer quantity;
}
