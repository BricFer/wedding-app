package com.example.weddingapp.repository;

import com.example.weddingapp.entity.Invitation;
import com.example.weddingapp.entity.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    // Encontrar invitaciones pendientes de una boda
    @Query("SELECT i FROM Invitation i " +
            "WHERE i.guest.wedding = :wedding " +
            "AND i.status = 'PENDING'")
    List<Invitation> findPendingByWedding(@Param("wedding") Wedding wedding);
}
