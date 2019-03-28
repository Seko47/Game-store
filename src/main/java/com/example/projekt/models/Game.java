package com.example.projekt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table ( name = "games" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game implements Serializable
{
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column ( nullable = false )
    @NotBlank
    private String name;

    @Column ( name = "description_short", nullable = false, columnDefinition = "TEXT" )
    @Size ( max = 300 )
    @NotBlank
    private String descriptionShort;

    @Column ( name = "description_long", nullable = false, columnDefinition = "TEXT" )
    @NotBlank
    private String descriptionLong;

    @Column ( nullable = false )
    @PositiveOrZero
    @NotNull
    @NumberFormat ( style = NumberFormat.Style.CURRENCY )
    private BigDecimal price;

    @Column ( name = "cover_URI" )
    private String coverURI;

    private String image1;

    private String image2;

    private String image3;

    @Column ( nullable = false )
    @NotBlank
    private String publisher;

    @Column ( nullable = false )
    @NotBlank
    private String producer;

    @Column ( name = "release_date", nullable = false )
    @Past
    @NotNull
    @Temporal ( TemporalType.DATE )
    private Date releaseDate;

    @ManyToMany ( fetch = FetchType.EAGER )
    private List< GameCategory > gameCategories;
}
