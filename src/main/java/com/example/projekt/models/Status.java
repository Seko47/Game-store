package com.example.projekt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table ( name = "statuses" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status implements Serializable
{
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Integer id;

    @NotBlank
    private String name;
}
