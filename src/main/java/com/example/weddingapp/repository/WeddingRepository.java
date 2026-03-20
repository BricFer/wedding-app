package com.example.weddingapp.repository;

import com.example.weddingapp.entity.User;
import com.example.weddingapp.entity.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface WeddingRepository extends JpaRepository<Wedding, Long> {

    /*List<Wedding> findByCreatedByOrderByWeddingDateDesc(User user);

    List<Wedding> findByWeddingDateBetween(LocalDate startDate, LocalDate endDate);

    List<Wedding> findAllWithGuestsForUser(@Param("user") User user
    );*/
}
