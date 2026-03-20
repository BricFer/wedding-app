package com.example.weddingapp.service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

import com.example.weddingapp.entity.Wedding;
import com.example.weddingapp.repository.InvitationRepository;
import com.example.weddingapp.repository.WeddingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.weddingapp.entity.Guest;
import com.example.weddingapp.repository.GuestRepository;

@Service
@AllArgsConstructor
public class GuestService {

    private GuestRepository guestRepo;
    private WeddingRepository weddingRepo;
    private InvitationRepository invitationRepo;

    /**
     * Method use to create or update a guest
     * @param guest Guest to be added or updated
     * @return Guest added or updated
     */
    public Guest saveGuest(Guest guest) throws AccessDeniedException {

        Wedding wedding = weddingRepo.findById(guest.getWedding().getId())
                .orElseThrow(() -> new EntityNotFoundException("Wedding not found"));

        if(!wedding.getCreatedBy().getId().equals(guest.getCreatedBy().getId())) {
            throw new AccessDeniedException("You don't have the required permissions");
        }

        if(guestRepo.findByWeddingAndEmail(wedding, guest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("This email already exist on the list.");
        }

        return guestRepo.save(guest);
    }

    /**
     * Method use to get a list of guests
     * @return List with all the guests registered
     */
    public List<Guest> getAllGuests() {
        return guestRepo.findAll();
    }

    // Optional<T> es una clase contenedora introducida en Java 8.
    // Sirve para representar un valor que puede o no estar presente.
    // En lugar de devolver directamente un objeto que podría ser null, Spring Data JPA devuelve un Optional<Guest> 
    // De esta forma se puede manejar el caso “no encontrado” sin usar null ni provocar un NullPointerException.
    public Optional<Guest> getGuestById(Long id) {
        return guestRepo.findById(id);
    }

    public void deleteGuest(Long id) {
        guestRepo.deleteById(id);
    }
}
