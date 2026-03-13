package com.example.weddingapp.service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.weddingapp.entity.Guest;
import com.example.weddingapp.repository.GuestRepository;

@Service
@AllArgsConstructor
public class GuestService {

    private GuestRepository guestRepository;

    /**
     * Method use to create or update a guest
     * @param guest Guest to be added or updated
     * @return Guest added or updated
     */
    public Guest saveGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    /**
     * Method use to get a list of guests
     * @return List with all the guests registered
     */
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    // Optional<T> es una clase contenedora introducida en Java 8.
    // Sirve para representar un valor que puede o no estar presente.
    // En lugar de devolver directamente un objeto que podría ser null, Spring Data JPA devuelve un Optional<Guest> 
    // De esta forma se puede manejar el caso “no encontrado” sin usar null ni provocar un NullPointerException.
    public Optional<Guest> getGuestById(Long id) {
        return guestRepository.findById(id);
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }

    public List<Guest> getConfirmedGuests() {
        return guestRepository.findByHasConfirm(true);
    }

    public List<Guest> getPendingGuest() {
        return guestRepository.findByHasConfirm(false);
    }
}
