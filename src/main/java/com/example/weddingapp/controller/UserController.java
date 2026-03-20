package com.example.weddingapp.controller;

import java.util.logging.Logger;

import com.example.weddingapp.entity.User;
import com.example.weddingapp.service.WebbAppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.weddingapp.classes.Pages;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());

    private final WebbAppUserService service;

    @GetMapping("/add-user")
    public String addUser() {
        return Pages.REGISTER_PAGE;
    }

    @PostMapping("/register")
    // TODO: Ver cómo debería pasar la información del nuevo usuario sin usar directamente el User
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            User savedUser = service.saveUser(user);

            if(savedUser.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("User created successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("it was not possible to create register the user");
            }
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception ocurred: "+e.getMessage());
        }
    }

    @GetMapping("/edit-profile")
    public String showEditForm(Model model) {

        // TODO: Implementar la lógica para que el form reciba la información del usuario loggeado
        // y poder modificar la información que quiero.

        return Pages.EDIT_PROFILE;
    }

    @PostMapping("/confirmation")
    public String updateProfile(@ModelAttribute("userModel") User user) {

        try {
            service.saveUser(user);

        } catch(Exception e) {
            LOG.warning("Error: " + e.getMessage());
            // TODO: Redireccionar al dashboard e informar que ha ocurrido un error
        }

        return Pages.DASHBOARD_PAGE;
    }

    // TODO: Revisar la lógica de este método
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {

        try {
            service.deleteUser(id);
        } catch(Exception ex) {
            // TODO: Implementar
        }
        return Pages.LOGOUT_PAGE;
    }
}
