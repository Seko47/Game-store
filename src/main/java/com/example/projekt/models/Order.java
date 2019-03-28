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
import java.util.Set;

@Entity
@Table ( name = "orders" )
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable
{
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn ( name = "user_id", nullable = false )
    private User user;

    @Column ( name = "order_details" )
    @NotNull
    @ManyToMany ( fetch = FetchType.EAGER )
    private Set< OrderDetail > orderDetails;

    @NotNull
    @PastOrPresent
    @Temporal ( TemporalType.TIMESTAMP )
    private Date date;

    @Column ( name = "total_value", nullable = false )
    @PositiveOrZero
    @NotNull
    @NumberFormat ( style = NumberFormat.Style.CURRENCY )
    private BigDecimal totalValue;

    @ManyToOne
    @JoinColumn ( name = "status_id", nullable = false )
    private Status status;

    public Order ( User user )
    {
        this.user = user;
    }
}
