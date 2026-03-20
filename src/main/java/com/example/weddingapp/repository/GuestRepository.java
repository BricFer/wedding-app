package com.example.weddingapp.repository;

import com.example.weddingapp.entity.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.weddingapp.entity.Guest;
import java.util.List;
import java.util.Optional;

/* No es necesario crear la interface de GuestRepository ya que Spring Data JPA puede generar todo el CRUD automáticamente */
@Repository

// JpaRepository es una interfaz que ya define muchos métodos, como save(entity), findAll(id), deleteById(id), etc
public interface GuestRepository extends JpaRepository<Guest, Long> {
    // Se pueden añadir métodos personalizados si se necesitan
    // Aunque con Spring Data JPA, Spring genera automáticamente una implementación de esa interfaz en tiempo de ejecución.
    // Es decir, no es necesario escribir el código de save(), findById()
    List<Guest> findByWeddingOrderByName(Wedding wedding);

    // Encontrar invitado específico
    Optional<Guest> findByWeddingAndEmail(Wedding wedding, String email);

    // Contar invitados confirmados
    long countByWeddingAndConfirmed(Wedding wedding, boolean confirmed);
}
