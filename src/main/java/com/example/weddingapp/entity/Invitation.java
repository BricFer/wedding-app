package com.example.weddingapp.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.weddingapp.enums.InvitationStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Entity representing an invitation that has been sent.
 * Mapped using JPA anotations
 * 
 * @author BricFer
 * @version 1.0
 * 
 */
@Entity
@Table(name = "invitations")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Invitation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invitation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

    @Column(name = "sent_at", nullable = true)
    private LocalDateTime sentAt;

    @Column(name = "confirmed_at", nullable = true)
    private LocalDateTime confirmedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public boolean isConfirmed() {
        return this.status == InvitationStatus.CONFIRMED;
    }
}
