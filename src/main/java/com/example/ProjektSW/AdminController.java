package com.example.ProjektSW;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Klasa będąca kontrolerem widoku panelu administratora.
 */
@Controller
public class AdminController {

    /**
     * Metoda odpowiadająca za wywołanie widoku panelu administratora.
     */
    @GetMapping("/admin")
    public String getAdminPanel() {
        return "admin";
    }
}
