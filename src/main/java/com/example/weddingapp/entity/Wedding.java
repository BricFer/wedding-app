package com.example.weddingapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Entity representing a wedding.
 * Mapped using JPA anotations
 * 
 * @author BricFer
 * @version 1.0
 * 
 */
@Entity
@Table(name = "weddings")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Wedding {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wedding_id")
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDateTime date;

    @Column
    private String location;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
