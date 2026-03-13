package com.example.weddingapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Entity representing a registered User in DB.
 * Mapped using JPA anotations
 * 
 * @author BricFer
 * @version 1.0
 * 
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name="username", nullable = false, unique = true)
    private String username;

    @Column(name="pwd", nullable = false)
    private String password;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

    /**
     * Constructor without the attributte id use for new users
     *
     * @param username Nickname use by the user
     * @param password Code to validate the user access
     * @param email User's electronic address
     * @param isAdmin Attributte that identifies the user as admin or not
     */
    public Customer(String username, String password, String email, Boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = (isAdmin != null) && isAdmin;
    }
}
