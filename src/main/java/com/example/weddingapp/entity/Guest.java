package com.example.weddingapp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "guests", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"wedding_id", "email"})
})
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Guest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wedding_id", nullable = false)
    private Wedding wedding;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    private String email;

    @Column
    private String phone;
    
    @Column
    private String relationship;

    private String dietaryRestrictions;

    @Column(nullable = false)
    private Integer numberOfGuests = 1;
    
    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invitation> invitations = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private Customer createdBy;
    
    @ManyToOne
    @JoinColumn(name = "updated_by_id")
    private Customer updatedBy;

    public boolean isConfirmed() {
        return invitations.stream().anyMatch(Invitation::isConfirmed);
    }
}
