package com.example.weddingapp.controller;

import org.springframework.web.bind.annotation.GetMapping;

import com.example.weddingapp.classes.Pages;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Con @RestController, Spring trata el valor retornado como respuesta HTTP directa (texto plano o JSON).
// Si uso solo @Controller, Spring buscará en los recursos un jsp con ese nombre
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return Pages.DASHBOARD_PAGE;
    }
}
