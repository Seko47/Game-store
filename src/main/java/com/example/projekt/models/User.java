package com.example.projekt.models;

import com.example.projekt.annotation.AvailableUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table ( name = "users" )
@Getter
@Setter
@NoArgsConstructor
public class User
{
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank
    @AvailableUsername
    private String username;

    @NotBlank
    private String password;

    @Transient
    private String passwordConfirm;

    private boolean enabled = false;

    private String activationCode;

    @ManyToMany ( fetch = FetchType.EAGER )
    @JoinTable ( name = "users_roles", joinColumns = @JoinColumn ( name = "user_id" ), inverseJoinColumns = @JoinColumn ( name = "role_id" ) )
    private Set< Role > roles;

    @Column ( nullable = false )
    @NotBlank
    private String city;

    @Column ( name = "post_code", nullable = false )
    @NotBlank
    @Pattern ( regexp = "^\\d{2}-\\d{3}$" )
    private String postCode;

    @Column ( nullable = false )
    @NotBlank
    private String street;

    @Column ( name = "house_number", nullable = false )
    @NotBlank
    @Pattern ( regexp = "^[1-9]\\d*\\w?$" )
    private String houseNumber;

    @Column ( name = "apartment_number" )
    @Pattern ( regexp = "^([1-9]\\d*)?$" )
    private String apartmentNumber;

    @Column ( nullable = false )
    @NotBlank
    @Email
    private String email;

    @Column ( nullable = false )
    @NotBlank
    private String name;

    @Column ( nullable = false )
    @NotBlank
    private String surname;

    @Column ( name = "phone_number", nullable = false )
    @NotBlank
    @Pattern ( regexp = "^(?:\\(?\\+?48)?(?:[-\\.\\(\\)\\s]*(\\d)){9}\\)?$" )
    private String phoneNumber;

    @Column ( name = "credit_card_number", nullable = false )
    @NotBlank
    @CreditCardNumber ( ignoreNonDigitCharacters = true )
    private String creditCardNumber;

    public User ( String username )
    {
        this ( username, false );
    }

    public User ( String username, boolean enabled )
    {
        this.username = username;
        this.enabled = enabled;
    }

    @AssertTrue
    public boolean isPasswordsEquals ()
    {
        return password == null || passwordConfirm == null || password.equals ( passwordConfirm );
    }

}
