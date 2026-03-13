package com.example.weddingapp.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Guest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private Long id;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    private String email;

    @Column
    private String phone;

    @Column(name = "has_confirm", nullable = false)
    private boolean hasConfirm;

    @Override
    public String toString() {
        return String.format("Nombre Completo: %s%nCorreo: %s%nTeléfono: %s%n¿Ha confirmado?: %b", fullname, email, phone, hasConfirm);
    }
}
