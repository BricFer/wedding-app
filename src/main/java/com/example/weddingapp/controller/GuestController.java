package com.example.weddingapp.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.weddingapp.classes.Pages;
import com.example.weddingapp.entity.Guest;
import com.example.weddingapp.service.GuestService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guests")
public class GuestController {

    private static final Logger LOG = Logger.getLogger(GuestController.class.getName());

    private final GuestService guestService;
    private String guestModel = "guest";

    // TODO: Incluir la captura de errores personalizados
    // TODO: Agregar página para el catch en todos los métodos
    // TODO: Revisar que las rutas de los métodos se correspondan con las páginas que espero mostrar en cada caso

    @GetMapping("/saludo")
    public String guestSaludo() {
        return Pages.GUEST_LIST_PAGE;
    }
   @GetMapping("/guest-list")
    public String getGuests(Model model) {

        try {
            List<Guest> guests = guestService.getAllGuests();
            model.addAttribute("guests", guests);
            return Pages.GUEST_LIST_PAGE;

        } catch(Exception e) {
            LOG.severe("Error al obtener lista de invitados: " + e.getMessage());
        }
        return Pages.LOGIN_PAGE;
    }

    @GetMapping("/new-guest")
    public String newGuest(Model model) {
        model.addAttribute(guestModel, new Guest());
        return Pages.ADD_GUEST_PAGE;
    }

    @PostMapping("/add-guest")
    public String addGuest(@ModelAttribute("guestModel") Guest guest) {
        
        try {
            guestService.saveGuest(guest);
        } catch(Exception e) {
            LOG.severe("Error al insertar un nuevo invitado: " + e.getMessage());
        }
        
        return Pages.REDIRECT_TO_GUEST_LIST;
    }

    @GetMapping("/edit-guest/{id}")
    public String editGuestForm(@PathVariable("id") Long id, Model model) {
        try {
            Optional<Guest> guest = guestService.getGuestById(id);
            model.addAttribute(guestModel, guest);

        } catch (Exception e) {
            LOG.severe("Error al obtener los datos del invitado: " + e.getMessage());
        }
        return Pages.UPDATE_GUEST_PAGE;
    }

    @GetMapping("/confirmation")
    public String showConfirmFormString(Model model) {
        
        model.addAttribute(guestModel, new Guest());
        return Pages.CONFIRMATION_PAGE;
    }
    
    @PostMapping("/delete-guest/{id}")
    public String deleteGuest(@PathVariable("id") Long id) {

        try {
            guestService.deleteGuest(id);
        } catch (Exception e) {
            LOG.severe("Error al eliminar el invitado: " + e.getMessage());
        }

        return Pages.REDIRECT_TO_GUEST_LIST;
    }

}
